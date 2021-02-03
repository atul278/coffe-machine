package com.assignment.coffee.service.impl;

import com.assignment.coffee.controllers.model.Beverages;
import com.assignment.coffee.controllers.model.Machine;
import com.assignment.coffee.service.CoffeeService;
import com.assignment.coffee.service.TaskToExecute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CoffeeServiceImpl implements CoffeeService {


    @Override
    public Object getPossibleBeverages(Map<String,Object> params) throws InterruptedException {
        List<Beverages> beveragesList = new ArrayList<>();

        Map<String,Object> machineObject= (Map<String, Object>) params.get("machine");
        Map<String,Integer> totalItemsQuantity= (Map<String, Integer>) machineObject.get("total_items_quantity");
        Map<String,Object> rawBeverages= (Map<String, Object>) machineObject.get("beverages");

        Machine machine = new Machine();
        machine.setName("Dunzo");
        machine.setOutlets(((Map<String,Integer>)machineObject.get("outlets")).get("count_n"));
        machine.setTotalAvailableIngredients(totalItemsQuantity);

        rawBeverages.entrySet().forEach(rawBeverage->{
            Beverages beverage = new Beverages();
            beverage.setName(rawBeverage.getKey());
            beverage.setIngredients((Map<String, Integer>) rawBeverage.getValue());
            beveragesList.add(beverage);

        });

        List<String> response = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(machine.getOutlets());
        System.out.println("----------------------------------------------------");

        beveragesList.stream().forEach(beverages -> {
            executorService.execute(new TaskToExecute(machine,beverages,response) );
        });

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("----------------------------------------------------");

        return response;

    }



}
