package us.codecraft.webmagic.scheduler;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Store urls and cursor in files so that a Spider can resume the status when
 * shutdown.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class FileCacheQueueScheduler extends DuplicateRemovedScheduler
		implements MonitorableScheduler,Closeable {

	private String filePath = System.getProperty("java.io.tmpdir");

	private String fileUrlAllName = ".urls.txt";

	private Task task;

	private String fileCursor = ".cursor.txt";

	private PrintWriter fileUrlWriter;

	private PrintWriter fileCursorWriter;

	private AtomicInteger cursor = new AtomicInteger();

	private AtomicBoolean inited = new AtomicBoolean(false);

	private BlockingQueue<Request> queue;
	
	private ScheduledExecutorService scheduledFlushService ;

	// private Set<String> urls;

	public FileCacheQueueScheduler(String filePath) {
		if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
			filePath += "/";
		}
		this.filePath = filePath;
	}
	/**
	 * 可以动态设置queue 如PriorityBlockingQueue
	 * @param filePath
	 * @param queue
	 */
	public FileCacheQueueScheduler(String filePath, BlockingQueue<Request> queue) {
		this(filePath);
		this.queue = queue;
	}

	private void flush() {
		fileUrlWriter.flush();
		fileCursorWriter.flush();
	}

	private void init(Task task) {
		this.task = task;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		readFile();
		initWriter();
		initFlushThread();
		inited.set(true);
		logger.info("init cache scheduler success");
	}


	private void initFlushThread() {
		if(scheduledFlushService==null)
			scheduledFlushService = Executors.newScheduledThreadPool(1);
		
		scheduledFlushService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				flush();
			}
		}, 10, 10, TimeUnit.SECONDS);
	}

	private void initWriter() {
		try {
			fileUrlWriter = new PrintWriter(new FileWriter(
					getFileName(fileUrlAllName), true));
			fileCursorWriter = new PrintWriter(new FileWriter(
					getFileName(fileCursor), false));
			fileCursorWriter.println(cursor.get());
			fileCursorWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException("init cache scheduler error", e);
		}
	}

	private void readFile() {
		try {
			if(queue==null) //若为空 默认为LinkedBlockingQueue
				queue = new LinkedBlockingQueue<Request>();
			// urls = new LinkedHashSet<String>();
			readCursorFile();
			readUrlFile();
		} catch (FileNotFoundException e) {
			// init
			logger.info("init cache file " + getFileName(fileUrlAllName));
		} catch (IOException e) {
			logger.error("init file error", e);
		}
	}

	private void readUrlFile() throws IOException {
		String line;
		BufferedReader fileUrlReader = null;
		try {
			fileUrlReader = new BufferedReader(new FileReader(
					getFileName(fileUrlAllName)));
			int lineReaded = 0;
			while ((line = fileUrlReader.readLine()) != null) {
				// urls.add(line.trim());
				getDuplicateRemover().isDuplicate(new Request(line), null);
				lineReaded++;
				if (lineReaded > cursor.get()) { // 表示从中断处继续
					queue.add(new Request(line));
				}
			}
		} finally {
			if (fileUrlReader != null) {
				IOUtils.closeQuietly(fileUrlReader);
			}
		}
	}

	private void readCursorFile() throws IOException {
		BufferedReader fileCursorReader = null;
		try {
			fileCursorReader = new BufferedReader(new FileReader(
					getFileName(fileCursor)));
			String line;
			// read the last number
			while ((line = fileCursorReader.readLine()) != null) {
				cursor = new AtomicInteger(NumberUtils.toInt(line));
			}
		} finally {
			if (fileCursorReader != null) {
				IOUtils.closeQuietly(fileCursorReader);
			}
		}
	}

	private String getFileName(String filename) {
		return filePath + task.getUUID() + filename;
	}

	@Override
	protected void pushWhenNoDuplicate(Request request, Task task) {
		if (!inited.get()) {
			init(task);
		}
		queue.add(request);
		if (request.getExtra(Request.ONLY_ADD_TO_QUEUE) == null)
			fileUrlWriter.println(request.getUrl());
	}

	@Override
	public synchronized Request poll(Task task) {
		if (!inited.get()) { 
			init(task);
		}
		Request result = queue.poll();
		if (result != null
				&& result.getExtra(Request.ONLY_ADD_TO_QUEUE) == null)
			fileCursorWriter.println(cursor.incrementAndGet());
		return result;
	}

	@Override
	public int getLeftRequestsCount(Task task) {
		return queue.size();
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		return getDuplicateRemover().getTotalRequestsCount(task);
	}

	/**
	 *   如定时抓取 重置cursor文件避免无限增长
	 */
	public void resetCursorFile() {
		fileCursorWriter.flush(); // 先将缓冲区中的内容刷新到文件
		PrintWriter tempCursorWriter = null;
		try {
			tempCursorWriter = new PrintWriter(new FileWriter(
					getFileName(fileCursor), false));
			tempCursorWriter.println(cursor.get());
			tempCursorWriter.flush();

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally{
			if(tempCursorWriter != null)
				tempCursorWriter.close();
		}
	}

	/**
	 * 释放资源 如 writer 定时flush任务
	 */
	@Override
	public void close() {
		logger.info("close()");
		flush();
		fileUrlWriter.close();
		fileCursorWriter.close();
		scheduledFlushService.shutdown();
	}
}
