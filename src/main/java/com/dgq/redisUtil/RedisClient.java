package com.dgq.redisUtil;

import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * <b> redis 客户端访问 </b>
 * 
 * @author Administrator
 * 
 */
public class RedisClient {

	public static JedisPool jedisPool;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("config");

		int maxAction = Integer.parseInt(rb.getString("redis.pool.maxActive"));
		int maxIdle = Integer.parseInt(rb.getString("redis.pool.maxIdle"));
		int maxWait = Integer.parseInt(rb.getString("redis.pool.maxWait"));
		String host = rb.getString("redis.ip");
		int port = Integer.parseInt(rb.getString("redis.port"));

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxAction); // 设置最大连接数
		config.setMaxIdle(maxIdle); // 设置最大空闲数
		config.setMaxWaitMillis(maxWait); // 设置超时时间

		// 初始化连接池
		jedisPool = new JedisPool(config, host, port);
	}

	/**
	 * 向缓存中添加字符串内容
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中添加对象
	 * 
	 * @param key
	 * @param o
	 * @return
	 */
	public static boolean set(String key, Object o) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			String value = JSON.toJSONString(o);

			jedis.set(key, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中添加列表
	 * 
	 * @param key
	 * @param o
	 * @return
	 */
	public static boolean add(String key, Object o) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			String value = JSON.toJSONString(o);

			jedis.lpush(key, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			jedis.del(key);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}

	};

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			String value = jedis.get(key);
			T o = JSON.parseObject(value, clazz);

			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	};

	/**
	 * 根据key 获取列表
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static List<String> gete(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			List<String> list = jedis.lrange(key, 0, 5);

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	};

	/**
	 * 根据key 获取java 对象集合
	 * 
	 * @param key
	 *            缓存的key
	 * @param clazz
	 *            集合内实例对象的Class
	 * @return
	 */
	public static <T> List<T> getCollectionWithJavaObj(String key,
			Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			String jsons = jedis.get(key);

			return JSONArray.parseArray(jsons, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	};
}
