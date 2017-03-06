package com.dgq.redisUtil;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dgq.domain.Joke;

public class RedisTest {

	public static void main(String[] args) {

		Joke joke1 = new Joke("dgq1", "/icon.png", "哈哈哈1", "/img.png", "第id爱戴",
				12, 23, "https://www.baidu.com");
		Joke joke2 = new Joke("dgq2", "/icon.png", "哈哈哈2", "/img.png", "第id爱戴",
				12, 23, "https://www.baidu.com");
		Joke joke3 = new Joke("dgq3", "/icon.png", "哈哈哈3", "/img.png", "第id爱戴",
				12, 23, "https://www.baidu.com");

		ArrayList<Joke> list = new ArrayList<Joke>();
		list.add(joke1);
		list.add(joke2);
		list.add(joke3);
		ArrayList<Joke> list2 = new ArrayList<Joke>();
		list2.add(joke1);
		list2.add(joke2);
		list2.add(joke3);

		ArrayList<List<Joke>> list3 = new ArrayList<List<Joke>>();
		list3.add(list);
		list3.add(list2);
		RedisClient.set("list3", list3);

		boolean b = true;

		if (b) {
			List<Joke> jokes = RedisClient.getCollectionWithJavaObj("list3",
					Joke.class);

			System.out.println(jokes.get(0));
		} else {
			System.out.println("缓存失败");
		}
	}
}
