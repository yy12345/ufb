package com.ufufund.ufb.common.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	private static JedisPool pool = new JedisPool(new JedisPoolConfig(),"139.196.26.14");
	
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	public static void returnJedis(Jedis jedis){
		jedis.close();
	}  
	
}
