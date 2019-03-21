package com.rongrong.wework.contact;

import com.rongrong.wework.Restful;
import com.rongrong.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * @author rongrong
 * @Title: Contact
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 21:55
 * @email 1163565402@qq.com
 */
public class Contact extends Restful {
    String random = String.valueOf(System.currentTimeMillis());
    public Contact(){
        reset();
    }

    public void reset(){
        requestSpecification =given()
                .log().all()
                .queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON);

        requestSpecification.filter((req,res,ctx)->{
            //对请求，响应做封装
            req.queryParam("");
            return ctx.next(req,res);
        });
    }


}
