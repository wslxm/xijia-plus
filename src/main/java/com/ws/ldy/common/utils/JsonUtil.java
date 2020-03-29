package com.ws.ldy.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.*;

@SuppressWarnings("all")
public class JsonUtil {

	public static Object getObjectJsonString(String jsonString, Class<?> objclass) {
		Object obj;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		obj = JSONObject.toBean(jsonObject, objclass);
		return obj;
	}

	/**
	 * 从json对象字符串格式中得到一个map对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> getMapJson(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> iter = jsonObject.keys();
		Object obj;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			obj = jsonObject.get(key);
			valueMap.put(key, obj);
		}

		return valueMap;
	}

	/**
	 * 
	 * 从json对象字符串中获取一个object数组对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayJson(String jsonString) {
		JSONArray jsonarry = JSONArray.fromObject(jsonString);
		return jsonarry.toArray();
	}

	/**
	 * 从json对象字符串中获取一个list对象
	 * 
	 * @param jsonString
	 * @param jsonClass
	 * @return
	 */

	public static List<Object> getListJson(String jsonString, Class<?> jsonClass) {
		JSONArray jsonarray = JSONArray.fromObject(jsonString);
		// Object obj;
		// JSONObject jsonobject;
		List<Object> valueList = new ArrayList<Object>();
		for (Object object : jsonarray) {
			// jsonobject = (JSONObject) object;
			// obj = JSONObject.toBean(jsonobject, jsonClass);
			valueList.add(object);
		}
		return valueList;
	}

	/**
	 * 从json对象中获取字符串对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static String[] getStringArrayJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		String[] jsonStringArray = new String[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {
			jsonStringArray[i] = jsonArray.getString(i);
		}
		return jsonStringArray;
	}

	/**
	 * 将java类转换为json数据
	 * 
	 * @param obj
	 * @return
	 */

	public static String getJsonString(Object obj) {

		JSONObject jsonobject = null;
		try {
			JsonConfig jsonConfig = new JsonConfig();
			DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss");
			jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
			jsonobject = JSONObject.fromObject(obj, jsonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonobject.toString();

	}

	/**
	 * 将java数组数据转换为json数据
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object[] obj) {
		JsonConfig jsonConfig = new JsonConfig();
		DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss");
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
		return jsonArray.toString();
	}

	/**
	 * 将list数据转换为json数据
	 * 
	 * @param list
	 * @return
	 */
	public static String getJsonString(List<?> list) {
		JSONArray jsonArray = null;
		try {
			JsonConfig jsonConfig = new JsonConfig();
			DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss");
			jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
			jsonArray = JSONArray.fromObject(list, jsonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toString();
	}

	/**
	 * 将map数据转换为json数据
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonString(Map<?, ?> map) {
		JsonConfig jsonConfig = new JsonConfig();
		DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss");
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		return jsonObject.toString();
	}
}
