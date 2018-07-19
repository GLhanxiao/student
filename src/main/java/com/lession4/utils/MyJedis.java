package com.lession4.utils;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJedis {
	private static JedisPool pool = null;
	private static JedisPoolConfig poolConfig = null;
	static{
		 //redis连接池配置
		 poolConfig = new JedisPoolConfig();
		 poolConfig.setMinIdle(15);
		 poolConfig.setMaxTotal(100);
		 //创建连接池
		 pool = new JedisPool(poolConfig , "112.74.175.54", 8087,100000);
		}
	//获得jedis资源的方法
	public static Jedis getJedis(){
		 Jedis resource = pool.getResource();
		 resource.auth("123456");
		 return resource;
		 
	}
}
