package com.sjl.remotedb.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename CollectionUtils.java
 * @time 2019/7/18 14:17
 * @copyright(C) 2019 song
 */
public class CollectionUtils {


    /**
     * 把map的key转换成驼峰命名
     *
     * @param map
     * @return
     */
    public static void mapKeyToCamelCaseName(Map<String, Object> map) {
        Map hashMap = new HashMap();
        if (hashMap != null) {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) iterator.next();
                String key = entry.getKey();
                hashMap.put(StringUtils.underlineToCamel(key), map.get(key));
            }
            map.clear();
            map.putAll(hashMap);
        }
    }

}
