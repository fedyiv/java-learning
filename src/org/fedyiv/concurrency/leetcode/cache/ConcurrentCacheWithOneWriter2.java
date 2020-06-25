package org.fedyiv.concurrency.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public class ConcurrentCacheWithOneWriter2<K, V> {

    private volatile Map<K, V> map;

    public  void put(K key, V value) {

        Map<K,V> tempMap = new HashMap<>();
        if(map != null)
            tempMap.putAll(map);
        tempMap.put(key,value);
        map = tempMap;

    }

    public  V get(K key) {
        return map.get(key);

    }

}
