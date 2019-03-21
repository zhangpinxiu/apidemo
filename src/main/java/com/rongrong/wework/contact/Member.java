package com.rongrong.wework.contact;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * @author rongrong
 * @Title: Member
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 12:30
 * @email 1163565402@qq.com
 */
public class Member extends Contact{
    /**
     * 创建成员
     * @param map
     * @return
     */
    public Response create(HashMap<String,Object> map){
        String body= template("/data/member.json",map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    /**
     * 读取成员
     * @param userid 成员UserID
     * @return
     */
    public Response getMemberMessage(String userid){
        reset();
        return requestSpecification
                .queryParam("userid",userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
    }

    /**
     * 更新成员
     * @param map
     * @return
     */
    public Response updateMember(HashMap<String,Object> map){
        String body = template("/data/updateMember.json",map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all().extract().response();
    }

    /**
     * 删除成员
     * @param userid
     * @return
     */
    public Response deleteMember(String userid){
        reset();
        return requestSpecification
                .queryParam("userid",userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }


    /**
     * 批量删除成员
     * @param map
     * @return
     */
    public Response batchDeleteMember(HashMap<String,Object> map){
        String body = template("/data/batchDeleteMember.json",map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
                .then().log().all().extract().response();
    }

    /**
     * 获取部门成员
     * @param department_id
     * @param fetch_child 1/0：是否递归获取子部门下面的成员 这个好像有问题都递归循环了
     * @return
     */
    public Response listSimple(int department_id,int fetch_child){
        reset();
        return requestSpecification
                .queryParam("department_id",department_id)
                .queryParam("fetch_child",fetch_child)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all().extract().response();
    }


    /**
     * 获取部门成员详情
     * @param department_id
     * @param fetch_child 1/0：是否递归获取子部门下面的成员 这个好像有问题都递归循环了
     * @return
     */
    public Response list(int department_id,int fetch_child){
        reset();
        return requestSpecification
                .queryParam("department_id",department_id)
                .queryParam("fetch_child",fetch_child)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/list")
                .then().log().all().extract().response();
    }


    /**
     * userid转openid
     * @param userid
     * @return
     */
    public Response convert_to_openid(String userid){
        //todo 不成功一直报错 errmsg": "invalid userid, hint: [1553089845_4_08586450b4ce7bd76b36bb168ead7e15]
        reset();
        return requestSpecification
                .queryParam("userid",userid)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid")
                .then().log().all().extract().response();
    }

    /**
     * 二次验证
     * @param userid
     * @return
     */
    public Response authsucc(String userid){
        reset();
        return requestSpecification
                .queryParam("userid",userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/authsucc")
                .then().log().all().extract().response();
    }

    /**
     * 邀请成员
     * @param map
     * @return
     */
    public Response invite(HashMap<String,Object> map){
        String body = template("/data/invite.json",map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/batch/invite")
                .then().log().all().extract().response();
    }
}
