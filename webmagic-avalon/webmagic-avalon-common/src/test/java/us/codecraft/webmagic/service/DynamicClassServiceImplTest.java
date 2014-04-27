package us.codecraft.webmagic.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.forger.ForgerFactory;
import us.codecraft.webmagic.AbstractTest;
import us.codecraft.webmagic.Foo;
import us.codecraft.webmagic.dao.DynamicClassDao;
import us.codecraft.webmagic.exception.DynamicClassCompileException;
import us.codecraft.webmagic.service.impl.DynamicClassServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * @author code4crafter@gmail.com
 */
public class DynamicClassServiceImplTest extends AbstractTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Spy
    @Autowired
    private ForgerFactory forgerFactory;

    @InjectMocks
    private DynamicClassService dynamicClassService = new DynamicClassServiceImpl();

    @Mock
    private DynamicClassDao dynamicClassDao;

    @Test
    public void testCompileAndSave() throws Exception {
        Class aClass = dynamicClassService.compileAndSave(Foo.SOURCE_CODE);
        assertThat(aClass.getCanonicalName()).isEqualTo("us.codecraft.webmagic.Foo");
    }

    @Test
    public void testCompileFail() {
        try {
            dynamicClassService.compileAndSave("class s((");
            failBecauseExceptionWasNotThrown(DynamicClassCompileException.class);
        } catch (DynamicClassCompileException e) {
        }
    }
}
