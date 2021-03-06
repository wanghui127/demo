package com.example.demo.entity.wechat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.utils.EmptyUtil;

import net.sf.json.JSONObject;

/** 说明：参数封装Map */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageData extends HashMap implements Map {

	private static final long serialVersionUID = -1302811066779944372L;
	Map map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				if (values.length > 1) {
					returnMap.put(name + "_values", values);
				}

				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		Object obj = get(key);
		if (EmptyUtil.isNotEmpty(obj)) {
			return obj.toString();
		}
		return null;
	}

	public Integer getInteger(Object key) {
		Object obj = get(key);
		if (EmptyUtil.isNotEmpty(obj)) {
			return Integer.valueOf(obj.toString());
		}
		return null;
	}

	public Long getLong(Object key) {
		Object obj = get(key);
		if (EmptyUtil.isNotEmpty(obj)) {
			return Long.valueOf(obj.toString());
		}
		return null;
	}

	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	public JSONObject toJson() {
		return JSONObject.fromObject(map);
	}

}
