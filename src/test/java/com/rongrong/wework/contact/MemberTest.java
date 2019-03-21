package com.rongrong.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rongrong
 * @Title: MemberTest
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/18 22:04
 * @email 1163565402@qq.com
 */
class MemberTest {
    static Member member;
    @BeforeAll
    public static void setUp(){
        member=new Member();
    }

    @Test
    void create() {
        HashMap<String,Object> map= new HashMap<String, Object>();
        map.put("userid","pinxiu_"+member.random);
        map.put("name","pinxiu_"+member.random);
        map.put("alias","pinxiu_"+member.random);
        map.put("department",Arrays.asList(1,2));
        map.put("mobile","151"+member.random.substring(0,8));
        map.put("email",member.random.substring(0,8)+"@qq.com");
        map.put("avatar_mediaid",null);
        member.create(map).then().statusCode(200).body("errcode",equalTo(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pinxiu_","rongrong_","test_"})
    void createWithValueSource(String name) {
        String nameNew = name+member.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String,Object> map= new HashMap<String, Object>();
        map.put("userid",nameNew);
        map.put("name",nameNew);
        map.put("alias",nameNew);
        map.put("department",Arrays.asList(1,2));
        map.put("mobile","151"+random);
        map.put("email",random+"@qq.com");
        map.put("avatar_mediaid",null);
        member.create(map).then().statusCode(200).body("errcode",equalTo(0));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/getMemberMessage.csv")
    void getMemberMessage(String userid,String name) {
        member.getMemberMessage(userid).then().statusCode(200).body("errcode",equalTo(0)).body("name",equalTo(name));
    }

    @Test
    void updateMember() {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("userid","test_15529199809681552919980968");
        map.put("name","test_update2014");
        member.updateMember(map).then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void deleteMember() {
        //先新增
        String nameNew = "createForDelete"+member.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String,Object> map= new HashMap<String, Object>();
        map.put("userid",nameNew);
        map.put("name",nameNew);
        map.put("alias",nameNew);
        map.put("department",Arrays.asList(1,2));
        map.put("mobile","151"+random);
        map.put("email",random+"@qq.com");
        map.put("avatar_mediaid",null);
        member.create(map);
        //删除自己新增的
        member.deleteMember(nameNew).then().statusCode(200).body("errcode",equalTo(0)).body("errmsg",equalTo("deleted"));
    }


    @Test
    void batchDeleteMember() {
        //先新增
        String nameNew1 = "createForDelete1"+member.random;
        String nameNew2 = "createForDelete2"+member.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String,Object> map1= new HashMap<String, Object>();
        HashMap<String,Object> map2= new HashMap<String, Object>();
        map1.put("userid",nameNew1);
        map1.put("name",nameNew1);
        map1.put("alias",nameNew1);
        map1.put("department",Arrays.asList(1,2));
        map1.put("mobile","153"+random);
        map1.put("email",random+"@qq.com");
        map1.put("avatar_mediaid",null);

        map2.put("userid",nameNew2);
        map2.put("name",nameNew2);
        map2.put("alias",nameNew2);
        map2.put("department",Arrays.asList(1,2));
        map2.put("mobile","152"+random);
        map2.put("email",random+"@163.com");
        map2.put("avatar_mediaid",null);
        member.create(map1);
        member.create(map2);
         //再批量删除
        HashMap<String,Object> batchDeleteMap= new HashMap<String, Object>();
        batchDeleteMap.put("useridlist", Arrays.asList(nameNew1,nameNew2));
        member.batchDeleteMember(batchDeleteMap).then().statusCode(200).body("errcode",equalTo(0)).body("errmsg",equalTo("deleted"));
    }

    @Test
    void listSimple() {
        member.listSimple(1,1).then().statusCode(200).body("errcode",equalTo(0)).body("userlist[2].name",equalTo("pinxiu_1552919283498"));
        member.listSimple(1,0).then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void list() {
        member.list(1,1).then().statusCode(200).body("errcode",equalTo(0)).body("userlist[2].email",equalTo("15529192@qq.com"));
        member.list(1,0).then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void convert_to_openid() {
        member.convert_to_openid("ZhangPinXiu").then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void authsucc() {
        member.authsucc("ZhangPinXiu").then().statusCode(200).body("errcode",equalTo(0)).body("errmsg",equalTo("ok"));
    }

    @Test
    void invite() {
        HashMap<String,Object> map = new HashMap<String, Object>();
        member.invite(map).then().statusCode(200).body("errcode",equalTo(0));
    }
}