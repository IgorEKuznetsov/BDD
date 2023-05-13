package com.ui.data;

public enum PreparatoryCoursesData {
  JAVA("online/java"),
  CPP("online/online-cpp"),
  JS("online/online-js"),
  PHP("online/php"),
  CSHARP("online/online_csharp"),
  PROF_IT("online/it_professia");

  private String name;

  PreparatoryCoursesData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
