package com.lalalazero.todos.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lalalazero.todos.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    UserService userService;

    public static JWT instance = new JWT();

    public static JWT getInstance(){
        return instance;
    }

    public String newToken(String username){
        String headerEncoded = base64UrlEncode(header);
        String userPayload = payload.replace("temp",username);
        String payloadEncoded = base64UrlEncode(userPayload);
        String signature = getSignature(headerEncoded,payloadEncoded);
        String jwt_token = new StringBuilder(headerEncoded)
                .append(".").append(payloadEncoded).append(".").append(signature).toString();
        return jwt_token;
    }

    private String getSignature(String encodeHeader,String encodePayload){
        String data = new StringBuilder(encodeHeader).append(".").append(encodePayload).toString();
        String hashedData = AES.encrypt(data, myKey);
        String sign = base64UrlEncode(hashedData);
        return sign;
    }

    public Boolean isTokenValid(String jwt,String userName){
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

    public Boolean isTokenValid(String jwt){
        // 验证 jwt 没有被修改过
        String[] jwtArr = jwt.split("\\.");
        String encodePayload = jwtArr[1];
        String encodeHeader = jwtArr[0];
        String verifySignature = getSignature(encodeHeader,encodePayload);
        String signature = jwtArr[2];
        if(StringUtils.equals(signature,verifySignature)){
            // 验证用户真实性
            JSONObject obj = JSON.parseObject(base64UrlDecode(encodePayload));
            String username = obj.get("username").toString();
            return userService.isExist(username);
        }
        return false;
    }

    private String base64UrlEncode(String data){
        return Base64.getUrlEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    private String base64UrlDecode(String data){
        return new String(Base64.getUrlDecoder().decode(data),StandardCharsets.UTF_8);
    }

    public static void main(String[] args){
        JWT instance = JWT.getInstance();
        String jwt = instance.newToken("lalala");
        System.out.println("jwt: " + jwt);
        System.out.println(instance.isTokenValid(jwt,"lalala"));

    }

    public static void testBase64(String test){
        JWT instance = JWT.getInstance();
        System.out.println("原字符串: " + test);
        String a = instance.base64UrlEncode(test);
        System.out.println("base64编码后: " + a);
        String b = instance.base64UrlDecode(a);
        System.out.println("base64解码后: " + b);
    }
}
