package org.example;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class JsonApp {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        //write. Create Writer:
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("customer.json"));

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 2);
        customer.put("name", "abc");
        customer.put("email", "abc@kdj");
        customer.put("age", 19);

        Map<String, Object> address = new HashMap<>();
        address.put("city", "Hanoi");

        customer.put("address", address);
        customer.put("payment", Arrays.asList("Papayl", "Momo", "Apple Pay"));

        //Add project

        Map<String, Object> project1 = new HashMap<>();
        project1.put("shop", "snne");
        project1.put("budget", 2000);

        Map<String, Object> project2 = new HashMap<>();
        project2.put("sdsd", "snne");
        project2.put("budget", 2000);


        customer.put("payment", Arrays.asList("Papayl", "Momo", "Apple Pay"));
        customer.put("project", Arrays.asList(project1, project2));
        writer.write(gson.toJson(customer));
        writer.close();

        System.out.println(gson.toJson(customer));
        System.out.println("Final");
    }
}
