package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    public static void main(String[] args) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("customer.json"));
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
        System.out.println(parser);
        System.out.println(parser.get("id").getAsLong());

        JsonObject address = parser.get("address").getAsJsonObject();
        System.out.println(address.get("city").getAsString());

        for (JsonElement element : parser.get("payment").getAsJsonArray()) {
            System.out.println(element.getAsString());
        }
        for (JsonElement project : parser.get("project").getAsJsonArray()) {
            JsonObject object = project.getAsJsonObject();
            System.out.println(object.get("shop").getAsLong());
        }
    }
}
