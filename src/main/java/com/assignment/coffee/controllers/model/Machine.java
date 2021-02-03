package com.assignment.coffee.controllers.model;

import lombok.Data;
import java.util.Map;


/*
    Entity Encapsulating the Machine details with following field
    name = Name of machine
    outlets = Total number of outlet for parallelly puring of beverages
    totalAvailableIngredients = Map containing the ingredients and quantity in the machine
 */
@Data
public class Machine {

    private String name;
    private Integer outlets;
    private Map<String,Integer> totalAvailableIngredients;

}
