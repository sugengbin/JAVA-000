package com.gengbinsu.java0.netty.week02.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created by gengbinsu 2020年11月4日
 */
public class MyHttpClient {

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static Optional<String> doGet(URI uri) {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000)// 设置连接超时时间(单位毫秒)
				.setConnectionRequestTimeout(5000)// 设置请求超时时间(单位毫秒)
				.setSocketTimeout(5000)// socket读写超时时间(单位毫秒)
				.setRedirectsEnabled(true).build();// 设置是否允许重定向(默认为true)
		Optional<String> result = Optional.empty();
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setConfig(requestConfig);
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse response = httpClient.execute(httpGet)) {
			HttpEntity responseEntity = response.getEntity();
			if (Objects.nonNull(responseEntity)) {
				result = Optional.ofNullable(EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static Optional<String> doGet(String url, Map<String, String> params) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(url);
		Optional.ofNullable(params).ifPresent(sk -> {
			for (Entry<String, String> entry : sk.entrySet()) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue());
			}
		});
		return doGet(uriBuilder.build());
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	public static Optional<String> doGet(String url) throws URISyntaxException {
		return doGet(new URIBuilder(url).build());
	}

	//----post
	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static Optional<String> doPost(URI uri, StringEntity se) {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000)// 设置连接超时时间(单位毫秒)
				.setConnectionRequestTimeout(5000)// 设置请求超时时间(单位毫秒)
				.setSocketTimeout(5000)// socket读写超时时间(单位毫秒)
				.setRedirectsEnabled(true).build();// 设置是否允许重定向(默认为true)
		Optional<String> result = Optional.empty();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setConfig(requestConfig);
		if (Objects.nonNull(se)) {
			httpPost.setEntity(se);
		}
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse response = httpClient.execute(httpPost)) {
			HttpEntity responseEntity = response.getEntity();
			if (Objects.nonNull(responseEntity)) {
				result = Optional.ofNullable(EntityUtils.toString(responseEntity));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	public static Optional<String> doPost(String url) throws URISyntaxException {
		return doPost(new URIBuilder(url).build(), null);
	}
	
	// Test
	public static void main(String[] args) throws URISyntaxException {
		Optional<String> result = MyHttpClient.doGet("http://localhost:8808/test");
		result.ifPresent(rtn -> System.out.println(rtn));
		
	    result = MyHttpClient.doPost("http://localhost:8808/test");
		result.ifPresent(rtn -> System.out.println(rtn));
	}
}
