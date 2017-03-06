package com.dgq.redisUtil;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonUtils {

	public static <T> T JsonToJavaObj(JSON json, Class<T> clazz) {

		T t = JSON.toJavaObject(json, clazz);

		return t;
	}

	public static <T> List<T> JsonCollectionToJavaObj(String jsons,
			Class<T> clazz) {

		List<T> list = JSONArray.parseArray(jsons, clazz);

		return list;
	}
}
