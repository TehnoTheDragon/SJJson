package com.tehnodragon.javalearn.datastore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataStore
{
    private final Map<String, StoreScope> scopeMap = new HashMap<>();
    private final String dataStoreName;
    private final File dataStoreFile;

    private static String dataStringReformator(String string)
    {
        string = string.replaceAll(" ", "_");
        return string;
    }

    public DataStore(String name)
    {
        this.dataStoreName = dataStringReformator(name);
        this.dataStoreFile = new File(this.dataStoreName);

        if (!dataStoreFile.exists())
            dataStoreFile.mkdirs();
    }

    // Check & Preload files
    public void CAPFiles()
    {
        File[] files = dataStoreFile.listFiles();
        if (files != null)
            for (File file: files)
                if (file.getName().endsWith(".json"))
                    getOrCreateScope(file.getName().replaceAll(".json", ""));
    }

    // Save all files
    public void GlobalSave()
    {
        CAPFiles();
        for (StoreScope storeScope: scopeMap.values())
            storeScope.Save();
    }

    public boolean HasScope(String scopeName)
    {
        return scopeMap.containsKey(dataStringReformator(scopeName));
    }

    public StoreScope GetScope(String scopeName)
    {
        return scopeMap.get(dataStringReformator(scopeName));
    }

    public StoreScope getOrCreateScope(String scopeName)
    {
        scopeName = dataStringReformator(scopeName);

        if (!HasScope(scopeName))
            scopeMap.putIfAbsent(scopeName, new StoreScope(scopeName, this));
        return GetScope(scopeName);
    }

    public String getDataStoreName() {
        return dataStoreName;
    }
}
