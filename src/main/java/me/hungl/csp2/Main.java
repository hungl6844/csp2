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
    HashMap<String, String> variables = new HashMap<>();
    
    while(storyRunning) {
      Config currentConfig = scenes.get(nextConfig);
      if (currentConfig.needs != null) {
        if (currentConfig.needs.size() > 0) {
          for (Map.Entry<String, String> set :  currentConfig.needs.entrySet()) {
            if (set.getValue().contains(";")) {
              String[] strings = set.getValue().split(";");
              System.out.println(strings[0]);
              for (int x = 1; x < strings.length; x++) {
                System.out.println(strings[x]);
              }
              variables.put(set.getKey(), strings[input.nextInt()].replaceAll("[ -1234567890\n\r]", "").trim());
            } else {
              System.out.println(set.getValue());
              variables.put(set.getKey(), input.next());
            }
          }
        }
      }
      
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
      String choice = "exit";
      if (currentConfig.options.size() > 0) {
        choice = currentConfig.options.get(input.next());
        if (choice.equals("exit")) {
          storyRunning = false;
          continue;
        }
      }
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
