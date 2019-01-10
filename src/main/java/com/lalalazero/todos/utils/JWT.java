package com.lalalazero.todos.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
/**
 * @Date 2019/1/8 下午5:22
 */
public class JWT {

//    jwt = header.payload.signature
//    data = base64urlEncode( header ) + “.” + base64urlEncode( payload )
//    hashedData = hash( data, secret )
//    signature = base64urlEncode( hashedData )

    private static String myKey = "lalalazero";
    private static String header = "{\"typ\":\"JWT\",\"alg\":\"AES\"}";
    private static String payload = "{\"username\": \"temp\"}";
    private static String headerEncoded = base64UrlEncode(header);

    public static String newToken(String username){
        String userPayload = payload.replace("temp",username);
        String payloadEncoded = base64UrlEncode(userPayload);
        String signature = getSignature(headerEncoded,payloadEncoded);
        String jwt_token = new StringBuilder(headerEncoded)
                .append(".").append(payloadEncoded).append(".").append(signature).toString();
        return jwt_token;
    }

    private static String getSignature(String encodeHeader,String encodePayload){
        String data = new StringBuilder(encodeHeader).append(".").append(encodePayload).toString();
        String hashedData = AES.encrypt(data, myKey);
        String sign = base64UrlEncode(hashedData);
        return sign;
    }

    public static Boolean isTokenValid(String jwt,String userName){
        String[] jwtArr = jwt.split("\\.");
        String encodePayload = jwtArr[1];
        JSONObject obj = JSON.parseObject(base64UrlDecode(encodePayload));
        String payloadUserName = obj.get("username").toString();
        if(!payloadUserName.equals(userName)){
            return false;
        }
        String encodeHeader = jwtArr[0];
        String verifySignature = getSignature(encodeHeader,encodePayload);
        String signature = jwtArr[2];
        if(StringUtils.equals(signature,verifySignature)){
            return true;
        }
        return false;
    }

    private static String base64UrlEncode(String data){
        return Base64.getUrlEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String base64UrlDecode(String data){
        return new String(Base64.getUrlDecoder().decode(data),StandardCharsets.UTF_8);
    }

    public static void main(String[] args){
        String jwt = newToken("lalala");
        System.out.println("jwt: " + jwt);
        System.out.println(isTokenValid(jwt,"lalala"));

    }

    public static void testBase64(String test){
        System.out.println("原字符串: " + test);
        String a = base64UrlEncode(test);
        System.out.println("base64编码后: " + a);
        String b = base64UrlDecode(a);
        System.out.println("base64解码后: " + b);
    }
}
