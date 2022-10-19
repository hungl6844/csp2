package me.hungl.csp2;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;

class Main {
  
  public static void main(String[] args) throws IOException {
    File configFile = new File(System.getProperty("user.dir") + "/data.yml");
    if (!configFile.exists()) {
      System.out.println("no data found!");
      System.exit(1);
    }
  }
}