package cn.inmobi.blr.springmockserver.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

public class MD5Util {
    public static String MD5Lower(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            return new BigInteger(1,  md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * for db  app_list_version
     * @param list
     * @return
     */
    public static String sortListToString(List<String> list){
        Collections.sort(list);
        return  String.join(",",list);
    }
}


