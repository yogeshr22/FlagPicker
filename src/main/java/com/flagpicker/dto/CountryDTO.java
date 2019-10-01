package com.flagpicker.dto;

public class CountryDTO {
  private String name;
  private String flag;
  private int recentViews;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public int getRecentViews() {
    return recentViews;
  }

  public void setRecentViews(int recentViews) {
    this.recentViews = recentViews;
  }

}
