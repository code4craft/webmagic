package us.codecraft.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.dao.DynamicClassDao;
import us.codecraft.webmagic.model.DynamicClass;

/**
 * @author code4crafter@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-*.xml"})
@Transactional
public class DynamicClassDaoTest {

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
