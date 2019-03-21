package com.rongrong.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.rongrong.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author rongrong
 * @Title: Department
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 12:30
 * @email 1163565402@qq.com
 */
public class Department extends Contact{
    /**
     * 获取部门列表
     * @param id 部门id
     * @return
     */
    public Response list(String id){
        reset();
        Response response =requestSpecification
                .param("id",id)
        .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then().extract().response();
        reset();
        return response;
    }

    /**
     * 创建部门
     * @param name
     * @param parentid
     * @return
     */
    public Response create(String name,String parentid){
        reset();
        String body= JsonPath.parse(this.getClass()
            .getResourceAsStream("/data/create.json"))
            .set("$.name",name)
            .set("parentid",parentid).jsonString();

         return requestSpecification
                 .body(body)
                 .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                 .then().log().all().extract().response();
    }

    /**
     * 创建部门
     * @param map
     * @return
     */
    public Response create(HashMap<String,Object> map){
        reset();
        DocumentContext documentContext=JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/create.json"));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(),entry.getValue());
        });

        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().extract().response();
    }

    /**
     * 更新部门
     * @param name
     * @param parentid
     * @param id
     * @return
     */
    public Response update(String name,String parentid,String id){
        reset();
        String body= JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/update.json"))
                .set("$.name",name)
                .set("parentid",parentid)
                .set("id",id).jsonString();

        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().extract().response();
    }


    /**
     * 删除部门
     * @param id 部门ID
     * @return
     */
    public Response delete(String id){
        reset();
        return requestSpecification
                .queryParam("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().extract().response();
    }

    /**
     * 删除所有不存在数据的部门
     * @return
     */
    public Response deleteAll(){
        reset();
        List<Integer> idList=list("").then().log().all().extract().path("department.id");
        idList.forEach(id->delete(id.toString()));
        return null;
    }
}
