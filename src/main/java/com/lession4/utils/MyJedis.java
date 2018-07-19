package com.lession4.utils;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJedis {
	private static JedisPool pool = null;
	private static JedisPoolConfig poolConfig = null;
	static{
		 //redis���ӳ�����
		 poolConfig = new JedisPoolConfig();
		 poolConfig.setMinIdle(15);
		 poolConfig.setMaxTotal(100);
		 //�������ӳ�
		 pool = new JedisPool(poolConfig , "112.74.175.54", 8087,100000);
		}
	//���jedis��Դ�ķ���
	public static Jedis getJedis(){
		 Jedis resource = pool.getResource();
		 resource.auth("123456");
		 return resource;
		 
	}
}
