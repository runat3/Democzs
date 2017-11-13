package com.test.fastjson.model;

import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/26.
 */

public class Person
{
    /**
     * name : 张三
     * age : 10
     * sex : man
     */

    private String name;
    private String age;
    private String sex;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
