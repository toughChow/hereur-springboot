package com.toughchow.springbootcommon.cache.ehcache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * hibernate的二级缓存只能用于findById这类请求，对于findAll无效，需要在controller层加入注解。
 */
@Slf4j
public class CustomerCacheEventListener implements CacheEventListener {
    @Override
    public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
        log.info("ehcache removed. key = {}, value = {}", element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
        log.info("ehcache put. key = {}, value = {}", element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
        log.info("ehcache updated. key = {}, value = {}", element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementExpired(Ehcache ehcache, Element element) {
        log.info("ehcache expired. key = {}, value = {}", element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementEvicted(Ehcache ehcache, Element element) {
        log.info("ehcache evicted. key = {}, value = {}", element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyRemoveAll(Ehcache ehcache) {
        log.info("ehcache removed all. cache name = {}", ehcache.getName());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }

    @Override
    public void dispose() {
        log.info("ehcache dispose.");
    }
}
