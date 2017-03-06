package com.dgq.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dgq.domain.Joke;

public class Spider {

	/**
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<Joke> getContent(String path) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(path);
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");

		CloseableHttpResponse response = httpClient.execute(httpGet);

		HttpEntity entity = response.getEntity();

		String content = EntityUtils.toString(entity, "utf-8");

		Document document = Jsoup.parse(content);

		// StringBuffer buffer = new StringBuffer();
		List<Joke> list = new ArrayList<Joke>();

		Elements elements = document.select(".dzlist");
		// buffer.append("[");
		for (Element element : elements) {
			Element author = element.getElementsByClass("dzlist-top").get(0);
			String authorIcon = author.getElementsByClass("user-portrait")
					.get(0).getElementsByTag("img").get(0).attr("src");
			String authorName = author.getElementsByClass("dz-username").get(0)
					.getElementsByTag("a").html();
			String jumpUrl = author.getElementsByClass("dz-listMore").get(0)
					.getElementsByTag("a").get(0).attr("href");

			Elements main = element.getElementsByClass("dz-list-con").get(0)
					.getElementsByTag("a");
			String title = main.get(0).getElementsByTag("p").html();
			String imgUrl = main.get(1).getElementsByTag("img").get(0)
					.attr("src");

			Elements uporDownCount = element
					.getElementsByClass("dz_list_bottom").get(0)
					.getElementsByClass("dz_list_bottom_left").get(0)
					.getElementsByTag("a");
			String upCount = uporDownCount.get(0).html();
			String downCount = uporDownCount.get(1).html();

			Joke joke = new Joke(authorName, authorIcon, title, imgUrl, null,
					Integer.parseInt(upCount), Integer.parseInt(downCount),
					jumpUrl);
			list.add(joke);

		}

		EntityUtils.consume(entity);
		response.close();
		httpClient.close();

		return list;
	}

	public static void main(String[] args) {
		Integer a = 3;
	}
}
