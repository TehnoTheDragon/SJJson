package com.tehnodragon.javalearn.datastore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService
{
    public static String GetFileContent(File file)
    {
        String content = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) content = content.concat(scan.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String GetBeautifulJson(JsonObject jsonObject)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(jsonObject);
    }
}
