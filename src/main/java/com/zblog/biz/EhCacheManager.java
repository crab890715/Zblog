package com.zblog.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Statistics;

@Component
public class EhCacheManager {
	@Autowired
	private EhCacheCacheManager manager;

	/**
	 * 获取缓存数据分析
	 * 
	 * @return
	 */
	public Collection<Map<String, Object>> stats() {
		Collection<String> cns = manager.getCacheNames();
		List<Map<String, Object>> list = new ArrayList<>(cns.size());
		for (String cacheName : cns) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("name", cacheName);
			Ehcache cache = manager.getCacheManager().getEhcache(cacheName);
			Statistics gateway = cache.getStatistics();
			/* 毫秒 */
			temp.put("averageGetTime", gateway.getAverageGetTime());
			temp.put("hitCount", gateway.getCacheHits());
			temp.put("missCount", gateway.getCacheMisses());
			temp.put("size", gateway.getObjectCount());
			if (cache.isStatisticsEnabled()) {
				temp.put("hitRatio",
						(float) gateway.getCacheHits() / (gateway.getCacheHits() + gateway.getCacheMisses()));
			}

			list.add(temp);
		}

		return list;
	}

	public void clear(String cacheName) {
		Cache cache = manager.getCacheManager().getCache(cacheName);
		cache.removeAll();
		cache.clearStatistics();
	}

}
