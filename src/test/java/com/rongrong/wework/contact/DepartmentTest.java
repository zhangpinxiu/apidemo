package com.rongrong.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rongrong
 * @Title: DepartmentTest
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 12:40
 * @email 1163565402@qq.com
 */
class DepartmentTest {
    String random = String.valueOf(System.currentTimeMillis());
    Department department;
    @BeforeEach
    void setUp() {
        if(department==null){
            department=new Department();
            department.deleteAll();
        }
    }

    @Test
    void list() {
        department.list("").then().statusCode(200).body("department.name[1]", equalTo("demo"));
        department.list("1").then().statusCode(200).body("department.name[0]", equalTo("测试"));
    }


    @Test
    void create() {
        department.create("rongrongDepartment2"+random,"1").then().body("errcode", equalTo(0));
//        department.create("rongrongDepartment2","1").then().body("errcode", equalTo(60008));
//        department.create("seveniruby_d1", "33").then().body("errcode", equalTo(60008));
//        department.create("seveniruby_d1", "33").then().body("errcode", equalTo(60008));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/createWithDup.csv")
    void createWithDup(String name,Integer expectCode){
        department.create(name+random,"1").then().body("errcode", equalTo(0));
        department.create(name+random,"1").then().body("errcode", equalTo(expectCode));
    }

    @Test
    void createWithChinese() {
        department.create("品秀的部门"+random,"1").then().body("errcode", equalTo(0));
    }

    @Test
    void createByMap(){
        HashMap<String,Object> map = new HashMap<String,Object>(){
            {
                put("name",String.format("pinxiu_add_map%s",random));
                put("parentid","1");
            }
        };
        department.create(map).then().body("errcode",equalTo(0));
    }
    @Test
    void update() {
        String nameOld = "rongrongDepartment_forupdate_"+random;
        department.create(nameOld ,"1");
        Integer idInt = department.list("").path("department.find{ it.name=='"+nameOld+"' }.id");
        String id =String.valueOf(idInt);
        department.update("rongrongDepartment_forupdate2_"+random,"1",id).then().body("errcode", equalTo(0));
    }

    @Test
    void delete() {
        String nameOld = "rongrongDepartment_fordelete1"+random;
        department.create(nameOld ,"1");
        Integer idInt = department.list("").path("department.find{ it.name=='"+nameOld+"' }.id");
        String id =String.valueOf(idInt);
        department.delete(id).then().body("errcode", equalTo(0));

    }

    @Test
    void deleteAll() {
        department.deleteAll();
    }
}