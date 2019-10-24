package com.tavisca.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
public class ServiceController {

    public RestTemplate restTemplate;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @RequestBody Employee employee,  @RequestHeader("requestHeader") String header ){
//        Employee employee = new Gson().fromJson(payload, Employee.class);
        System.out.println(employee.toString());
        employeeRepository.save(employee);

        return "success";
    }
}
