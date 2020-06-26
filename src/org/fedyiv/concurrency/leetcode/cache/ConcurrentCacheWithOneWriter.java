package org.fedyiv.concurrency.leetcode.cache;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public interface ConcurrentCacheWithOneWriter<K, V> {

    void put(K key, V value);
    V get(K key);

}
