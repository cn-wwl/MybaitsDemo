package com.dao;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable
{
    private int Id;
    private String name;
    private String sex;
    private Date birthday;
    private String address;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "User [id=" + Id + ", username=" + name + ", sex=" + sex
                + ", birthday=" + birthday + ", address=" + address + "]";
    }
}
