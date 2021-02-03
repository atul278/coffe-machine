package com.assignment.coffee.controllers;


import com.assignment.coffee.controllers.model.Beverages;
import com.assignment.coffee.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;

    @GetMapping("/availability")
    public Object getPossibleBeverages(@RequestBody Map<String,Object> params) throws InterruptedException {
        return coffeeService.getPossibleBeverages(params);
    }

}
