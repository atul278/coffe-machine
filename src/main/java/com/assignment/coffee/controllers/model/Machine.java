package com.assignment.coffee.controllers.model;

import lombok.Data;
import java.util.Map;

@Data
public class Machine {

    private String name;
    private Integer outlets;
    private Map<String,Integer> totalAvailableIngredients;

}
