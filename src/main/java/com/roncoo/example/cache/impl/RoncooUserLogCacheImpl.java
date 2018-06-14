package com.roncoo.example.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.roncoo.example.bean.RoncooUserLog;
import com.roncoo.example.cache.RoncooUserLogCache;
import com.roncoo.example.dao.RoncooUserLogDao;

/**
 * @author wujing
 */
@CacheConfig(cacheNames = "roncooCache")
@Repository
public class RoncooUserLogCacheImpl implements RoncooUserLogCache {

	@Autowired
	private RoncooUserLogDao roncooUserLogDao;

	//表示该方法支持缓存，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，
	//而不需要再次执行该方法。
	@Cacheable(key = "#p0")
	@Override
	public RoncooUserLog selectById(Integer id) {
		System.out.println("查询功能，缓存找不到，直接读库, id=" + id);
		return roncooUserLogDao.findOne(id);
	}

	//@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
	@CachePut(key = "#p0")
	@Override
	public RoncooUserLog updateById(RoncooUserLog roncooUserLog) {
		System.out.println("更新功能，更新缓存，直接写库, id=" + roncooUserLog);
		return roncooUserLogDao.save(roncooUserLog);
	}

	// @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
	@CacheEvict(key = "#p0")
	@Override
	public String deleteById(Integer id) {
		System.out.println("删除功能，删除缓存，直接写库, id=" + id);
		return "清空缓存成功";
	}

}
