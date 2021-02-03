package com.assignment.coffee.controllers;


import com.assignment.coffee.controllers.model.Beverages;
import com.assignment.coffee.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;


    // Controller acting as a the entrypoint for the coffee machine request
    @PostMapping("/availability")
    public Object getPossibleBeverages(@RequestBody Map<String,Object> params) throws InterruptedException {
        return coffeeService.getPossibleBeverages(params);
    }

}
