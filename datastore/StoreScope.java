package com.tehnodragon.javalearn.datastore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StoreScope
{
    private JsonObject json;
    private final DataStore dataStore;
    private final String scopeName;
    private final File scopeFile;

    public StoreScope(String scopeName, DataStore dataStore)
    {
        this.scopeName = scopeName;
        this.dataStore = dataStore;
        this.scopeFile = new File(dataStore.getDataStoreName() + "/" + scopeName + ".json");

        Load();
    }

    public void Load()
    {
        if (!scopeFile.exists()) {
            try {
                scopeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String content = FileService.GetFileContent(scopeFile);
        if (content.isEmpty())
        {
            json = new JsonObject();
            Save();
        }
        json = JsonParser.parseString(content).getAsJsonObject();
    }

    public void Save()
    {
        try {
            FileWriter fileWriter = new FileWriter(scopeFile);
            fileWriter.write(FileService.GetBeautifulJson(json));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateJson(JsonObject json)
    {
        this.json = json;
    }

    public JsonObject GetJson()
    {
        return json.deepCopy();
    }
}
