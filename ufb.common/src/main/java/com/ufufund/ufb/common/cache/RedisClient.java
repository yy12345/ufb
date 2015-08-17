package com.ufufund.ufb.common.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	private static JedisPool pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
	
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	public static void returnJedis(Jedis jedis){
		jedis.close();
	}  
	
	public static void main(String args[]){
		Jedis jedis = RedisClient.getJedis();
		
		String a = jedis.get("d");
		
		System.out.println(a);
	}
}
