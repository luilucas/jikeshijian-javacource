package com.lucas.homework.dubbo.demo.api;

public class Account implements java.io.Serializable{
    private String name;
    private int dollar;
    private int rmb;

    public Account(){ }

    public Account(String name, int dollar, int rmb){
        this.name = name;
        this.dollar = dollar;
        this.rmb = rmb;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    public int getDollar() { return dollar; }

    public void setDollar(int dollar) {
        this.dollar = dollar;
    }

    public int getRmb() { return rmb; }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }
}
