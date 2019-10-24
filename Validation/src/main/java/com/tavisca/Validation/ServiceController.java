package com.tavisca.Validation;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class ServiceController {

    RestTemplate restTemplate;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ExceptionRepository exceptionRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody String payload, @RequestHeader("requestHeader") String header){

        JSONObject headerJSON = new JSONObject(header);
        restTemplate = new RestTemplate();
        Date date = new Date();
        Employee employee = new Gson().fromJson(payload, Employee.class);
        String isNotValid = employee.isNotValid();
        System.out.println(headerJSON.toString());
        System.out.println(isNotValid);
        System.out.println("\n\n\n");
        if(!isNotValid.equals("none")){
            ExceptionalModel exceptionalModel = new ExceptionalModel();
            String txnId = headerJSON.get("transactionId").toString();
            exceptionalModel.id = txnId;
            exceptionalModel.message = isNotValid + " is not valid";
            exceptionalModel.serviceName = "Validation Service";
            exceptionalModel.timestamp = date.toString();

            exceptionRepository.save(exceptionalModel);

            Request request = new Request();
            request.payload = payload;
            request.requestFrom = headerJSON.getString("requestFrom");
            request.serviceName = "Validation Service";
            request.timeStamp = date.toString();
            request.transactionId = txnId;
            request.valid = false;

            requestRepository.save(request);
            System.out.println(exceptionalModel.toString());
            System.out.println(request.toString());
            return "failure";
        }
        else{
            String txnId = headerJSON.get("transactionId").toString();
            Request request = new Request();
            request.payload = payload;
            request.requestFrom = headerJSON.getString("requestFrom");
            request.serviceName = "Validation Service";
            request.timeStamp = date.toString();
            request.transactionId = txnId;
            request.valid = true;

            requestRepository.save(request);

            HttpHeaders headers = new HttpHeaders();

//            JSONObject header = new JSONObject();
//            header.put("requestFrom", "Validation Service");
//            header.put("transactionId", txnId);

            headers.set("requestHeader", header);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(payload, headers);
            System.out.println(entity.toString() + "\n\nyyuy");
            String responseEntity = restTemplate.postForObject("http://localhost:8082/addEmployee", entity, String.class);

            employeeRepository.save(employee);

            return responseEntity;
        }


    }
}
