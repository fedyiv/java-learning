package org.fedyiv.concurrency.leetcode.cache.impl;

import org.fedyiv.concurrency.leetcode.cache.ConcurrentCacheWithOneWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public class ConcurrentCacheWithOneWriterSynchronized<K, V> implements ConcurrentCacheWithOneWriter<K,V> {
    private final Map<K, V> map = new HashMap();

    @Override
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }


    @Override
    public synchronized V get(K key) {
        return map.get(key);
    }

}
