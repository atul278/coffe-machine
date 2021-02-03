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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CoffeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CoffeeService coffeeService;

/*
   Test using the default data from  https://www.npoint.io/docs/e8cd5a9bbd1331de326a
   picking from src/test/resources/dataDefault.json in this project

 */
    @Test
    public void getPossibleBeverages_validRequest_thenReturnRecord() throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream("dataDefault.json");
        StringBuffer sb = new StringBuffer();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mvc.perform(MockMvcRequestBuilders.post("/availability")
                .content(sb.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



/*
   Test using the data with Large Data
    picking from src/test/resources/dataLarge.json in this project

 */

    @Test
    public void getPossibleBeveragesWithLargeData_validRequest_thenReturnRecord() throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream("dataLarge.json");

        StringBuffer sb = new StringBuffer();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mvc.perform(MockMvcRequestBuilders.post("/availability")
                .content(sb.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
