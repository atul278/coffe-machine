package com.assignment.coffee.service;

import com.assignment.coffee.controllers.model.Beverages;
import com.assignment.coffee.controllers.model.Machine;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public class TaskToExecute implements Runnable {

    private Machine machine;
    private Beverages beverages;
    private List<String> response;

    public void run(){

        AtomicInteger ingredientFound = new AtomicInteger();

        AtomicReference<String> message = new AtomicReference<>("");

        System.out.println("STARTS " + beverages.getName());

        beverages.getIngredients().entrySet().forEach(ingredient -> {
            Integer available=null;
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


        if (ingredientFound.get()==beverages.getIngredients().size()){
            response.add(beverages.getName() +"  is prepared ");
        }

        else {
            response.add(beverages.getName() +message.get());
        }

        System.out.println("ENDS " + beverages.getName());

    }



}
