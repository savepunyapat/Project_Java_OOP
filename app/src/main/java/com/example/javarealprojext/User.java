package com.example.javarealprojext;

public class User {
    private String Status;
    private String Uid;
    public User(){}
    public User(String n,String nid){
        this.Uid = nid;
        this.Status = n;
    }
    public void setStatus(String st){
        this.Status = st;
    }
    public void setUid(String uid){
        this.Uid = uid;
    }
    public String getStatus(){
        return  this.Status;
    }
    public String getUid(){
        return this.Uid;
    }
}
