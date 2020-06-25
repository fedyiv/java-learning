package org.fedyiv.concurrency.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public class ConcurrentCacheWithOneWriter<K, V> {
    private final Map<K, V> map = new HashMap();

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }


    public synchronized V get(K key) {
        return map.get(key);
    }

}
