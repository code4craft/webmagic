package us.codecraft.webmagic.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.AbstractTest;
import us.codecraft.webmagic.model.DynamicClass;

/**
 * @author code4crafter@gmail.com
 */
public class DynamicClassDaoTest extends AbstractTest {

    @Autowired
    private DynamicClassDao dynamicClassDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testAdd() throws Exception {
        DynamicClass dynamicClass = new DynamicClass();
        dynamicClass.setClassName("test");
        dynamicClass.setSourceCode("testSource");
        dynamicClassDao.add(dynamicClass);
    }
}
