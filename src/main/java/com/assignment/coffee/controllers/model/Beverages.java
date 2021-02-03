package com.assignment.coffee.controllers.model;

import lombok.Data;
import java.util.Map;

@Data
public class Beverages {

    private String name;
    private Map<String,Integer> ingredients;

}
