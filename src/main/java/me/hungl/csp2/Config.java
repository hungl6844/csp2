package me.hungl.csp2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Config {
  @SerializedName("choices")
  HashMap<String, String> options;

  public String id;
  public String text;
}