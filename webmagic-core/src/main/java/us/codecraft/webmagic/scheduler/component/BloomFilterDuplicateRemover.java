package us.codecraft.webmagic.scheduler.component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BloomFilterDuplicateRemover for huge number of urls.
 *
 * @author code4crafer@gmail.com
 * @since 0.5.1
 */
public class BloomFilterDuplicateRemover implements DuplicateRemover {

    private int expectedInsertions;

    private double fpp;

    private AtomicInteger counter;

    public BloomFilterDuplicateRemover(int expectedInsertions) {
        this(expectedInsertions, 0.03);
    }

    public BloomFilterDuplicateRemover(int expectedInsertions, double fpp) {
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;
        this.bloomFilter = rebuildBloomFilter();
    }

    protected BloomFilter<CharSequence> rebuildBloomFilter() {
        counter = new AtomicInteger(0);
        return BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
    }

    private final BloomFilter<CharSequence> bloomFilter;

    @Override
    public boolean isDuplicate(Request request, Task task) {
        boolean isDuplicate = bloomFilter.mightContain(request.getUrl());
        if (!isDuplicate) {
            bloomFilter.apply(request.getUrl());
            counter.incrementAndGet();
        }
        return isDuplicate;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        rebuildBloomFilter();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return counter.get();
    }
}
