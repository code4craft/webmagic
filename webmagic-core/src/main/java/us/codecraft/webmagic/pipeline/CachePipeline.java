package us.codecraft.webmagic.pipeline;

import java.util.Collection;

/**
 * @author yaoqiang
 *
 * 为pipeline提供缓存能力
 * 在某个时机执行批处理任务
 */
public interface CachePipeline<T> extends Pipeline{

    /**
     * @param collection  缓存批处理
     *
     */
    void process(Collection<T> collection);
}
