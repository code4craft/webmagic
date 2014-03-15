package us.codecraft.webmagic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.forger.Forger;
import us.codecraft.forger.ForgerFactory;
import us.codecraft.webmagic.dao.DynamicClassDao;
import us.codecraft.webmagic.model.DynamicClass;
import us.codecraft.webmagic.service.DynamicClassService;

/**
 * @author code4crafter@gmail.com
 */
@Service
public class DynamicClassServiceImpl implements DynamicClassService {

    @Autowired
    private DynamicClassDao dynamicClassDao;

    @Autowired
    private ForgerFactory forgerFactory;

    @Override
    public String compileAndSave(String sourceCode) {
        Forger<Object> forger = forgerFactory.compile(sourceCode);
        String className = forger.getClazz().getCanonicalName();
        DynamicClass dynamicClass = new DynamicClass();
        dynamicClass.setClassName(className);
        dynamicClass.setSourceCode(sourceCode);
        dynamicClassDao.add(dynamicClass);
        return className;
    }

}
