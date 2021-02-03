package com.assignment.coffee.controllers.model;

import lombok.Data;
import java.util.Map;

/*
    Entity Encapsulating the Beverages details with following field
    name = Name of Beverage
    ingredients = Map containing the ingredients and quantity require to make the beverage
 */
@Data
public class Beverages {

    private String name;
    private Map<String,Integer> ingredients;

}
