package com.nextyu.study;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017-01-19 下午4:11
 *
 * @author nextyu
 */
public class MyTest {
    @Test
    public void test() {
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("skuId", 3133847);
        map1.put("选择容量", "32GB");
        map1.put("颜色", "褐色");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("skuId", 3133851);
        map2.put("选择容量", "128GB");
        map2.put("颜色", "金色");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        System.out.println(JSON.toJSONString(list));

        List<Map<String, Object>> list2 = JSON.parseObject(JSON.toJSONString(list), new TypeReference<List<Map<String, Object>>>() {
        });

        System.out.println(JSON.toJSONString(list2));

    }

}
