package com.assignment.coffee.service;

import com.assignment.coffee.controllers.model.Beverages;
import com.assignment.coffee.controllers.model.Machine;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/*
    Task executioner to pour the beverages from machine parellelly
 */
@AllArgsConstructor
public class TaskToExecute implements Runnable {

    private Machine machine;
    private Beverages beverages;
    private List<String> response;

    public void run(){

        AtomicInteger ingredientFound = new AtomicInteger();
        AtomicReference<String> message = new AtomicReference<>("");
        System.out.println("STARTS " + beverages.getName());

        //1. Checking the the presence of ingredient one by one from coffe machine

        beverages.getIngredients().entrySet().forEach(ingredient -> {
            Integer available=null;

            // 2.   Checking the particular ingredient in machine if preset and sufficient the pouring it .
            synchronized (machine) {
                 available = machine.getTotalAvailableIngredients().get(ingredient.getKey());
            }
                if (available ==null){
                    message.set(" cannot be prepared because " +ingredient.getKey()+" is not Available");
                }

                else if ( available >= ingredient.getValue()){
                    synchronized (machine) {
                        machine.getTotalAvailableIngredients().put(ingredient.getKey(),available-ingredient.getValue());
                    }
                    ingredientFound.getAndIncrement();
                }
                else{
                    message.set(" cannot be prepared because " + ingredient.getKey() + " is not Sufficient");
                }
        });

        // 3.   Checking if all the ingredient requirement is fulfilled.
        if (ingredientFound.get()==beverages.getIngredients().size()){
            response.add(beverages.getName() +"  is prepared ");
        }

        else {
            response.add(beverages.getName() +message.get());
        }

        System.out.println("ENDS " + beverages.getName());

    }



}
