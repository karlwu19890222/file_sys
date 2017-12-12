package cn.karlwu.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: RedisCacheTransfer.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common.cache
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月28日 下午3:48:37
 * @version: V1.0
 */
public class RedisCacheTransfer {
	@Autowired
	public void setJedisConnectionFactory(
			JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}

}
