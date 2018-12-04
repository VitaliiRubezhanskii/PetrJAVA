package com.petr.persistence.entity;

public enum MessageStatus {
    NEW ("NEW"),
    APPROVED ("APPROVED"),
    REJECTED ("REJECTED");

    private String value;
    MessageStatus(String value){
        this.value = value;
    }
    public  String getValue(){ return value;}
}
