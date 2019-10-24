package com.tavisca.InputGateway;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    public String transactionId;
    public String payload;
    public String timeStamp;
    public boolean valid;
    public String serviceName;
    public String requestFrom;

    public Request() {
    }

    public Request(String transactionId, String payload, String timeStamp, boolean valid, String serviceName, String requestFrom) {
        this.transactionId = transactionId;
        this.payload = payload;
        this.timeStamp = timeStamp;
        this.valid = valid;
        this.serviceName = serviceName;
        this.requestFrom = requestFrom;
    }

    @Override
    public String toString(){
        return "{\"transactionId\":"+this.transactionId+","+
                "\"payload\":" + this.payload + "," +
                "\"timestamp\":" + this.timeStamp + "," +
                "\"valid\":" + this.valid + "," +
                "\"serviceName\":" + this.serviceName + "," +
                "\"requestFrom\":" + requestFrom + "}";
    }
}
