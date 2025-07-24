package com.example.figureshop.util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class VnPayUtils {

	public static String buildQueryUrl(Map<String, String> params, String secretKey) throws UnsupportedEncodingException {
	    List<String> keys = new ArrayList<>(params.keySet());
	    Collections.sort(keys);

	    StringBuilder hashData = new StringBuilder();
	    StringBuilder query = new StringBuilder();

	    for (String key : keys) {
	        String value = params.get(key);
	        if ((value != null) && (!value.isEmpty())) {
	            hashData.append(key).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.name()))
		        .append('&');
	                   
	            query.append(URLEncoder.encode(key, StandardCharsets.US_ASCII));
	            query.append("=");
	            query.append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
	            query.append('&');
	          
	        }
	    }
		hashData.setLength(hashData.length() - 1);
		query.setLength(query.length() - 1);
		
		
	    String secureHash = hmacSHA512(secretKey, hashData.toString());
	    query.append("&vnp_SecureHash=").append(secureHash);
	    System.out.println("Đây là url: " + secureHash);
	    return query.toString();
	}




    public static boolean verifyResponse(Map<String, String> fields, String secretKey) {
        String hash = fields.remove("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");

        List<String> keys = new ArrayList<>(fields.keySet());
        Collections.sort(keys);

        StringBuilder hashData = new StringBuilder();
        for (String key : keys) {
            String value = fields.get(key);
            if ((value != null) && (!value.isEmpty())) {
                hashData.append(key).append("=").append(value).append("&");
            }
        }

        String secureHash = hmacSHA512(secretKey, hashData.substring(0, hashData.length() - 1));
        System.out.println("Đây là verify: " + secureHash);
        return secureHash.equalsIgnoreCase(hash);
    }

    public static String hmacSHA512(String key, String data) {
        try {
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            hmac512.init(secretKey);
            byte[] hashBytes = hmac512.doFinal(data.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : hashBytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}

