# SJJsonDB
- Small Java Json Data Base
- I don't really good at call a stuff.
- I did this thing for my minecraft project, I just needed json database, so I decided create my own.

## Small Description
```java
// It's will create manager of scopes
// name inside constructor is name of folder where will store an scopes
public static DataStore globalStore = new DataStore("global_store");

// It's way to create an scope
// name inside constructor is name of file no need put there .json <.<
StoreScope shop = globalStore.getOrCreateScope("shop");

// you can save all scopes use this method only
globalStore.GlobalSave();
// or you can do it manually for each scope
shop.Save()

// It's how you can get a JsonObject
JsonObject jsonObject = shop.GetJson();

// If you want put new data into your scope you need use this method
// But it's won't save it into file!
shop.UpdateJson(jsonObject)
```

# you need gson for using this things!
- I used such libraries.
```gradle
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
    implementation 'com.google.code.gson:gson:2.8.7'
}
```

## Shop Example
```java
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//import *.datastore.DataStore;
//import *.datastore.StoreScope;

public static MainClass
{
    public static DataStore globalStore = new DataStore("global_store");

    public static void main(String[] args)
    {
        globalStore.CAPFiles();
        StoreScope shop = globalStore.getOrCreateScope("shop");
        JsonObject jsonObject = shop.GetJson();

        JsonArray products = jsonObject.get("products").getAsJsonArray();

        for (JsonElement it: products)
        {
            JsonObject element = (JsonObject)it;

            String product_name = element.get("name").getAsString();
            double product_cost = element.get("cost").getAsDouble();

            System.out.println("Product: " + product_name + " | Cost: " + product_cost + "$");
        }

        globalStore.GlobalSave();
    }

    public static void addProduct(String product, double cost)
    {
        assert cost < 0.01;
        assert product != null;

        globalStore.CAPFiles();
        StoreScope shop = globalStore.getOrCreateScope("shop");
        JsonObject jsonObject = shop.GetJson();

        JsonArray products = jsonObject.get("products").getAsJsonArray();

        JsonObject productModel = new JsonObject();
        productModel.addProperty("name", product);
        productModel.addProperty("cost", cost);
        products.add(productModel);

        shop.UpdateJson(jsonObject);
    }
}
```
