package me.hungl.csp2;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.hungl.csp2.Config;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

class Main {
  
  public static void main(String[] args) throws IOException {

    Scanner input = new Scanner(System.in);
    
    File configFile = new File(System.getProperty("user.dir") + "/data.json");
    if (!configFile.exists()) {
      System.out.println("no data found!");
      System.exit(1);
    }
    FileReader reader = new FileReader(configFile);
    
    Type targetClassType = new TypeToken<ArrayList<Config>>() { }.getType();
    Collection<Config> config = new Gson().fromJson(reader, targetClassType);
    HashMap<String, Config> scenes = new HashMap<>();
    for (Config iterConfig : config) {
      scenes.put(iterConfig.id, iterConfig);
    }

    String nextConfig = "init";
    for (int x=0;x<config.size();x++) {
      Config currentConfig = scenes.get(nextConfig);
      System.out.println(currentConfig.text);
      if (x<config.size()) {
        System.out.println("What do you want to do?");
      }
      System.out.println(currentConfig.data.choice1);
      System.out.println(currentConfig.data.choice2);
      if (currentConfig.data.choice3 != null) { System.out.println("- " + currentConfig.data.choice3); }
      nextConfig = scenes.get(input.next());
    }
  }
}