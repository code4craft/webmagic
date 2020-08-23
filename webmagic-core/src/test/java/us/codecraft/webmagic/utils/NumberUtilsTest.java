package us.codecraft.webmagic.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testCompareLong() {
		Assert.assertEquals(0, NumberUtils.compareLong(0L, 0L));
		Assert.assertEquals(1, NumberUtils.compareLong(9L, 0L));
		Assert.assertEquals(-1, NumberUtils.compareLong(0L, 9L));
		Assert.assertEquals(-1, NumberUtils.compareLong(-9L, 0L));
		Assert.assertEquals(1, NumberUtils.compareLong(0L, -9L));
	}
}
