package us.codecraft.platform;

/**
 * 通用回调接口
 * 
 * @author 刘太信
 * @date 2017年8月18日 下午9:47:26
 */
@FunctionalInterface
public interface Callback<T> {
	/**
	 * 执行数据
	 * 
	 * @author 刘太信
	 * @date 2017年8月18日 下午9:47:46
	 * @param obj 对象
	 * @return 是否执行成功，或者是否继续执行等标识,根据业务自定义意义
	 */
	boolean execute(T obj);
}
