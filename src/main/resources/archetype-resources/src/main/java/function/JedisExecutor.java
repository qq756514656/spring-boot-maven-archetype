package ${package}.function;


import ${package}.exception.RedisConnectException;

/**
 * @author liusy
 */

@FunctionalInterface
public interface JedisExecutor<T, R> {
    /**
     * jedis 执行器
     *
     * @param t
     * @return R
     * @throws RedisConnectException
     */
    R excute(T t) throws RedisConnectException;
}
