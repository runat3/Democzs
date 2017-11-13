package com.test.fastjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.test.fastjson.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private String json = "{\n" +
            "    \"name\": \"张三\",\n" +
            "    \"age\": \"10\",\n" +
            "    \"sex\": \"man\"\n" +
            "}";

    private String jsonArray = "[\n" +
            "    {\n" +
            "        \"name\": \"张三\",\n" +
            "        \"age\": \"10\",\n" +
            "        \"sex\": \"man\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"李四\",\n" +
            "        \"age\": \"121\",\n" +
            "        \"sex\": \"man\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"王五\",\n" +
            "        \"age\": \"19\",\n" +
            "        \"sex\": \"woman\"\n" +
            "    }\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //json->javaObject
        Person person = JSON.parseObject(json, Person.class);
        Log.e("json->javaObject",person.toString());

        //json->List
        List<Person> persons = JSON.parseArray(jsonArray, Person.class);
        Log.e("json->List",persons.toString());

        //javaObject->json
        Person p1 = new Person();
        p1.setAge("12");
        p1.setName("asd");
        p1.setSex("man");
        String s1 = JSON.toJSONString(p1);
        Log.e("javaObject->json",s1);
        
        //List->json
        Person p2 = new Person();
        p2.setAge("112");
        p2.setName("a12sd");
        p2.setSex("woman");
        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        String s2 = JSON.toJSONString(list);
        Log.e("List->json",s2);

    }
}
