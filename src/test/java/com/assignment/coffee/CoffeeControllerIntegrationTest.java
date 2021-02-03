package com.assignment.coffee;

import com.assignment.coffee.service.CoffeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CoffeeControllerIntegrationTest {

    //TODO Add proper comments before sending

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CoffeeService coffeeService;

    private String requestRawJson = "{\"" +
            "machine\": {\"" +
                "outlets\":{\"count_n\":3},\"" +
            "beverages\":{\"" +
                "hot_tea\":{\"" +
                    "hot_milk\":100,\"" +
                    "hot_water\":200,\"" +
                    "sugar_syrup\":10,\"" +
                    "ginger_syrup\":10,\"" +
                    "tea_leaves_syrup\":30},\"" +
                "hot_coffee\":{\"" +
                    "hot_milk\":400,\"" +
                    "hot_water\":100,\"" +
                    "sugar_syrup\":50,\"" +
                    "ginger_syrup\":30,\"" +
                    "tea_leaves_syrup\":30},\"" +
                "black_tea\":{\"" +
                    "hot_water\":300,\"" +
                    "sugar_syrup\":50,\"" +
                    "ginger_syrup\":30,\"" +
                    "tea_leaves_syrup\":30},\"" +
                "green_tea\":{\"" +
                    "hot_water\":100,\"" +
                    "sugar_syrup\":50,\"" +
                    "ginger_syrup\":30,\"" +
                    "green_mixture\":30}},\"" +
            "total_items_quantity\":{\"" +
                "hot_milk\":500,\"" +
                "hot_water\":500,\"" +
                "sugar_syrup\":100,\"" +
                "ginger_syrup\":100,\"" +
                "tea_leaves_syrup\":100}}}";

    @Test
    public void getPossibleBeverages_validRequest_thenReturnRecord() throws Exception {

        ResultActions a =mvc.perform(MockMvcRequestBuilders.get("/availability")
                .content(requestRawJson)
                .contentType(MediaType.APPLICATION_JSON));
        a.andDo(MockMvcResultHandlers.print());

    }
}
