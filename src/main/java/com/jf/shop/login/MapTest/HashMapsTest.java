package com.jf.shop.login.MapTest;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class HashMapsTest {

    public static void main(String[] args) {

        HashMap<HashMaps,String> hs = new HashMap<>();

        hs.put(new HashMaps(1), "aa");
        hs.put(new HashMaps(1), "bb");
        hs.put(new HashMaps(2), "aa");
        hs.put(new HashMaps(3), "aa");

        System.out.println(JSONObject.toJSONString(hs));
        System.out.println(hs);
    }
}
