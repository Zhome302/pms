package com.ai.zhome.pms.content.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * <p>
 * <b>版权：</b>Copyright (c) 2020.<br>
 * <b>工程：</b>pms<br>
 * <b>文件：</b>HttpClientUtil.java<br>
 * <b>创建人：</b> wangzh<br>
 * <b>创建时间：</b>2020-3-23 下午3:02:07<br>
 * <p>
 * <b>HttpClientUtil类.</b><br>
 * HttpClientUtil类.<br>
 * </p>
 *
 * @author wangzh
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpClientUtil {
	/**
	 * 发送 Post请求
	 *
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String postJson(String url, String jsonStr) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = null;
		try {
			StringEntity s = new StringEntity(jsonStr);
			s.setContentEncoding("UTF-8");
			// 发送json数据需要设置contentType
			s.setContentType("application/json");
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = res.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");// 返回json格式：
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 发送 Post请求
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String postJson(String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = null;
		try {
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = res.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");// 返回json格式：
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (entity != null) {
				EntityUtils.consume(entity);// 关闭流
			}
		}
	}

	/**
	 * 发送 Post请求
	 *
	 * @param url
	 * @param timeout 超时时间，毫秒
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String postJson(String url, int timeout) throws Exception {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		HttpPost post = new HttpPost(url);
		HttpEntity entity = null;
		try {
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = res.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");// 返回json格式：
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (entity != null) {
				EntityUtils.consume(entity);// 关闭流
			}
		}
	}

	/**
	 * 发送 Get请求
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String getJson(String url) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpEntity entity = null;
		try {
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = res.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");// 返回json格式：
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (entity != null) {
				EntityUtils.consume(entity);// 关闭流
			}
		}
	}

}
