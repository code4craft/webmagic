package us.codecraft.webmagic.downloader.selenium;

/**
 * @author code4crafter@gmail.com
 *         Date: 2017/9/12
 *         Time: 下午10:57
 */
public class ScreenShotServiceImpl  {
    //
	//private GenericObjectPool<ChromeDriver> pool;
    //
	//private ChromeOptions options;
    //
    //
	//@PreDestroy
	//public void destroy(){
	//	pool.close();
	//}
    //
	//@PostConstruct
	//public void init(){
	//	System.getProperties().setProperty("webdriver.chrome.driver",
	//			"/usr/share/chromedriver");
	//	options = new ChromeOptions();
	//	options.addArguments("--headless","--disable-gpu");
	//	options.setExperimentalOption("mobileEmulation",
	//			ImmutableMap.builder().put("deviceMetrics",
	//					ImmutableMap.builder().put("width", 360).put("height", 640).put("pixelRatio", 3).build())
	//					.build());
	//	GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
	//	poolConfig.setMaxTotal(5);
	//	poolConfig.setMaxIdle(5);
	//	poolConfig.setMinIdle(1);
	//	pool = new GenericObjectPool<ChromeDriver>(
	//			new BasePooledObjectFactory<ChromeDriver>() {
	//				@Override
	//				public ChromeDriver create() throws Exception {
	//					return new ChromeDriver(options);
	//				}
    //
	//				@Override
	//				public PooledObject<ChromeDriver> wrap(ChromeDriver chromeDriver) {
	//					return new DefaultPooledObject<ChromeDriver>(chromeDriver) {
	//						@Override
	//						public synchronized void invalidate() {
	//							chromeDriver.quit();
	//							super.invalidate();
	//						}
	//					};
	//				}
	//			}, poolConfig);
    //
	//}
    //
	//@Override
	//public byte[] getMobileScreenShot(String url) {
	//	ChromeDriver driver;
	//	try {
	//		driver = pool.borrowObject();
	//	} catch (Exception e) {
	//		log.error("get from pool error", e);
	//		return new byte[] {};
	//	}
	//	try {
	//		driver.get(url);
	//		long width = (Long) driver.executeScript(
	//				"return Math.max(document.body.scrollWidth, document.body.offsetWidth, document.documentElement.clientWidth, document.documentElement.scrollWidth, document.documentElement.offsetWidth);");
	//		long height = (Long) driver.executeScript(
	//				"return Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");
	//		driver.manage().window().setSize(new Dimension((int) width + 100, (int) height + 100));
	//		byte[] bytes = driver.getScreenshotAs(OutputType.BYTES);
	//		BufferedImage img = null;
	//		try {
	//			img = ImageIO.read(new ByteArrayInputStream(bytes));
	//			BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
	//					img.getHeight(), BufferedImage.TYPE_INT_RGB);
	//			newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
	//			ByteArrayOutputStream output = new ByteArrayOutputStream();
	//			ImageIO.write(newBufferedImage, "jpg", output);
	//			log.info("get data bytes {}", bytes.length);
	//			return output.toByteArray();
	//		} catch (IOException e) {
	//			log.error("convert image error {}", bytes.length, e);
	//		}
	//		return bytes;
	//	} finally {
	//		pool.returnObject(driver);
	//	}
	//}
}
