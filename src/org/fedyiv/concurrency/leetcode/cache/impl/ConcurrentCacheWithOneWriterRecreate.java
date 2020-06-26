package org.fedyiv.concurrency.leetcode.cache.impl;

import org.fedyiv.concurrency.leetcode.cache.ConcurrentCacheWithOneWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public class ConcurrentCacheWithOneWriterRecreate<K, V> implements ConcurrentCacheWithOneWriter<K,V> {

    private volatile Map<K, V> map;

    @Override
    public  void put(K key, V value) {

        Map<K,V> tempMap = new HashMap<>();
        if(map != null)
            tempMap.putAll(map);
        tempMap.put(key,value);
        map = tempMap;

    }

    @Override
    public  V get(K key) {
        return map.get(key);

    }

}
