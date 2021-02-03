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


    /*
    Service containing business logic for coffee machine
  */
@Service
public class CoffeeServiceImpl implements CoffeeService {


    @Override
    public Object getPossibleBeverages(Map<String,Object> params) throws InterruptedException {
        List<Beverages> beveragesList = new ArrayList<>();


        // 1.   De-serialising the  "machine" ,"total_items_quantity" , "beverages" from request into Map

        Map<String,Object> machineObject= (Map<String, Object>) params.get("machine");
        Map<String,Integer> totalItemsQuantity= (Map<String, Integer>) machineObject.get("total_items_quantity");
        Map<String,Object> rawBeverages= (Map<String, Object>) machineObject.get("beverages");

        // 2. De-serialising  the  machineObject into to the "Machine" Entity

        Machine machine = new Machine();
        machine.setName("Dunzo");
        machine.setOutlets(((Map<String,Integer>)machineObject.get("outlets")).get("count_n"));
        machine.setTotalAvailableIngredients(totalItemsQuantity);

        // 3. De-serialising  Each type of "Beverages" from  rawBeverages  and  creating List of "Beverages"

        rawBeverages.entrySet().forEach(rawBeverage->{
            Beverages beverage = new Beverages();
            beverage.setName(rawBeverage.getKey());
            beverage.setIngredients((Map<String, Integer>) rawBeverage.getValue());
            beveragesList.add(beverage);

        });

        // 4. Creating Thread pool with size of total number of outlet in the coffee machine for parallel pouring of beverages

        List<String> response = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(machine.getOutlets());
        System.out.println("----------------------------------------------------");

        // 6. Invoking each  Thread one by one to pouring beverage from machine parallelly

        beveragesList.stream().forEach(beverages -> {
            executorService.execute(new TaskToExecute(machine,beverages,response) );
        });

        // 7. Waiting for all the beverages to be poured and returning response

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);


        System.out.println("----------------------------------------------------");

        return response;

    }



}
