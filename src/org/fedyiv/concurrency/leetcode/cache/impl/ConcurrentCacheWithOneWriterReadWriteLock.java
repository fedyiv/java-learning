package org.fedyiv.concurrency.leetcode.cache.impl;

import org.fedyiv.concurrency.leetcode.cache.ConcurrentCacheWithOneWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Let's assume we need to create a concurrent cache such as only one thread is writing and all others are reading from the cache.
 * Also let's use HashMap as underlying data storage.
 */
public class ConcurrentCacheWithOneWriterReadWriteLock<K, V> implements ConcurrentCacheWithOneWriter<K,V> {

    private final Map<K, V> map = new HashMap();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void put(K key, V value) {

        readWriteLock.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public V get(K key) {


        readWriteLock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
