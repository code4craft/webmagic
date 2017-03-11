package us.codecraft.webmagic.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;
import us.codecraft.webmagic.utils.HttpConstant;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/3/11
 *         Time: 上午11:26
 */
@RunWith(MockitoJUnitRunner.class)
public class DuplicateRemovedSchedulerTest {

    private DuplicateRemovedScheduler duplicateRemovedScheduler = new DuplicateRemovedScheduler() {
        @Override
        public Request poll(Task task) {
            return null;
        }
    };

    @Test
    public void test_no_duplicate_removed_for_post_request() throws Exception {
        DuplicateRemover duplicateRemover = Mockito.mock(DuplicateRemover.class);
        duplicateRemovedScheduler.setDuplicateRemover(duplicateRemover);
        Request request = new Request("https://www.google.com/");
        request.setMethod(HttpConstant.Method.POST);
        duplicateRemovedScheduler.push(request, null);
        verify(duplicateRemover,times(0)).isDuplicate(any(Request.class),any(Task.class));
    }

    @Test
    public void test_duplicate_removed_for_get_request() throws Exception {
        DuplicateRemover duplicateRemover = Mockito.mock(DuplicateRemover.class);
        duplicateRemovedScheduler.setDuplicateRemover(duplicateRemover);
        Request request = new Request("https://www.google.com/");
        request.setMethod(HttpConstant.Method.GET);
        duplicateRemovedScheduler.push(request, null);
        verify(duplicateRemover,times(1)).isDuplicate(any(Request.class),any(Task.class));
    }
}
