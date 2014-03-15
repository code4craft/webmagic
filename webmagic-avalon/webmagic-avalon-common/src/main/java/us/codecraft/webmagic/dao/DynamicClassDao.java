package us.codecraft.webmagic.dao;

import org.apache.ibatis.annotations.Insert;
import us.codecraft.webmagic.model.DynamicClass;

/**
 * @author code4crafter@gmail.com
 */
public interface DynamicClassDao {

    @Insert("insert into DynamicClass (`ClassName`,`SourceCode`,`AddTime`,`UpdateTime`) values (#{className},#{sourceCode},now(),now())")
    public int add(DynamicClass dynamicClass);
}
