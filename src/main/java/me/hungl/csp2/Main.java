package me.hungl.csp2;
import java.io.*;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException, InterruptedException {

    Scanner input = new Scanner(System.in);
    
    File configFile = new File(System.getProperty("user.dir") + "/data.json");
    if (!configFile.exists()) {
      System.out.println("no data found!");
      System.exit(1);
    }
    FileReader reader = new FileReader(configFile);

    Type mapOfConfig = new TypeToken<Collection<Config>>() {}.getType();
    Collection<Config> config = new GsonBuilder().create().fromJson(reader, mapOfConfig);
    HashMap<String, Config> scenes = new HashMap<>();
    for (Config iterConfig : config) {
      scenes.put(iterConfig.id, iterConfig);
    }

    // ask for name and class

    String nextConfig = "init";
    boolean storyRunning = true;
    while(storyRunning) {
      Config currentConfig = scenes.get(nextConfig);
      typeWriter(currentConfig.text);

      if (currentConfig.options.size() == 1) {
        nextConfig = currentConfig.options.get("1");
        continue;
      } else {
        System.out.println("What do you want to do?");
      }
      for (String choice : currentConfig.options.keySet()) {
        System.out.println("- " + choice + ": " + currentConfig.options.get(choice));
      }
      String choice = currentConfig.options.get(input.next().strip());
      if (choice.equals("exit")) {
        System.exit(0);
      }
      System.out.println(choice);
      nextConfig = choice;
    }
  }
  
  public static void typeWriter(String output) throws InterruptedException {
    for (char c : output.toCharArray()) {
      System.out.print(c);
      Thread.sleep(30);
    }
    System.out.println();
  }
}