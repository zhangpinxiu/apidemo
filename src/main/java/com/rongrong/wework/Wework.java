package com.rongrong.wework;

import com.rongrong.WeWorkConfig;
import io.restassured.RestAssured;

/**
 * @author rongrong
 * @Title: com.rongrong.wework.Wework
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 10:33
 * @email 1163565402@qq.com
 */
public class Wework {
    private static String token;
    /**
     * 获取token的方法
     * @return
     */
    public static String getWeworkToken(String secret){
        return RestAssured.given().log().all()
                .queryParam("corpid",WeWorkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().statusCode(200)
                .extract().path("access_token");
    }

    public static String getToken(){
        //todo: 支持两种类型的token
        if (token==null){
            token = getWeworkToken(WeWorkConfig.getInstance().contactSecret);
        }
        return token;
    }

}
