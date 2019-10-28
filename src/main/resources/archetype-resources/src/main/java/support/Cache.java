package ${package}.support;

import java.util.function.Supplier;

/**
 * 缓存
 *
 * @author liusy
 */
public class Cache {

    /**
     * 查询缓存
     *
     * @param cache
     * @param db
     * @param <T>
     * @return
     */
    public static <T> T of(Supplier<T> cache, Supplier<T> db) {
        T t = cache.get();
        if (t != null) {
            return t;
        }
        return db.get();
    }
}
