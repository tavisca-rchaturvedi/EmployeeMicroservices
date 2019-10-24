package com.tavisca.Validation;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class ExceptionalModel {
    @Id
    public String id;
    public String message;
    public String serviceName;
    public String timestamp;

    public ExceptionalModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ExceptionalModel(String id, String message, String serviceName, String timestamp) {
        this.id = id;
        this.message = message;
        this.serviceName = serviceName;
        this.timestamp = timestamp;
    }

    public String toString(){
        return "\"id\":"+ this.id+","+
                "\"message\":" + this.message+","+
                "\"serviceName\":"+ this.serviceName+","+
                "\"timestamp\":"+ this.timestamp+"}";
    }
}

