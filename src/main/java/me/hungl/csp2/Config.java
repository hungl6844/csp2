package me.hungl.csp2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Config {
  @SerializedName("choices")
  HashMap<String, String> options;

  @SerializedName("id")
  public String id;

  @SerializedName("text")
  public String text;

  @SerializedName("needs")
  HashMap<String, String> needs;
}