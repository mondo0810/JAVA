package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Repository {

    public List<User> users;
    public List<User> getData() throws FileNotFoundException {
        FileReader reader = new FileReader("account.json");
        //Chuyen tu JSON -> Object
        Type objectType = new TypeToken<List<User>>() {}.getType();
        users = new Gson().fromJson(reader, objectType);
        return users;
    }

}
