package com.tavisca.InputGateway;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

@RestController
public class ServiceController {

    public RestTemplate restTemplate;

    @Autowired
    ExceptionRepository exceptionRepository;

    @Autowired
    RequestRepository requestRepository;

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody String payload, @RequestHeader("requestHeader") JSONObject requestHeader){
        String txnId = UUID.randomUUID().toString();
        System.out.println(requestHeader);
        JSONObject payloadJSON = new JSONObject(payload);
        String response;
        Date date = new Date();
        Iterator<String> it = payloadJSON.keys();
        boolean invalid = false;

        while(it.hasNext()){
            String value = it.next();
            if(value.equals(null) || value.isEmpty()){
                invalid = true;
            }
        }

        if(invalid){
            ExceptionalModel exceptionalModel = new ExceptionalModel();
            exceptionalModel.id = txnId;
            exceptionalModel.message = "Null Check Not Successful";
            exceptionalModel.serviceName = "Input Gateway";
            exceptionalModel.timestamp = date.toString();

            Request request = new Request();
            request.transactionId = txnId;
            request.payload = payload;
            request.serviceName = "Input Gateway";
            request.timeStamp = date.toString();
            request.valid = false;
            request.requestFrom = requestHeader.getString("requestFrom");
            response = "JSON not correct";

            System.out.println(exceptionalModel.toString());
            System.out.println(request.toString());
            exceptionRepository.save(exceptionalModel);
            requestRepository.save(request);
        }
        else{
            restTemplate = new RestTemplate();
            Request request = new Request();
            request.transactionId = txnId;
            request.payload = payload;
            request.serviceName = "Input Gateway";
            request.timeStamp = date.toString();
            request.valid = true;
            request.requestFrom = requestHeader.getString("requestFrom");

            System.out.println("this is request " + request.toString());
            HttpHeaders headers = new HttpHeaders();
            System.out.println("Hello");

            JSONObject header = new JSONObject();
            header.put("requestFrom", "Input Gateway Service");
            header.put("transactionId", txnId.toString());

            headers.set("requestHeader", header.toString());
            HttpEntity<String> request1 = new HttpEntity<>(payload, headers);
            String responseValue = restTemplate.postForObject("http://localhost:8083/addEmployee", request1, String.class );

            response = responseValue;
            requestRepository.save(request);
        }

        return response;
    }
}
