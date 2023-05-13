package com.ui.data;

public enum MonthData {

  JANUARY("января", "January"),
  FEBRUARY("февраля", "February"),
  MARCH("марта", "March"),
  APRIL("апреля", "April"),
  MAY("мая", "May"),
  JUNE("июня", "June"),
  JULY("июля", "July"),
  AUGUST("августа", "August"),
  SEPTEMBER("сентября", "September"),
  OCTOBER("октября", "October"),
  NOVEMBER("ноября", "November"),
  DECEMBER("декабря", "Dec");
  private String nameRus;
  private String nameEng;

  MonthData(String nameRus, String nameEng) {
    this.nameRus = nameRus;
    this.nameEng = nameEng;
  }

  public String getNameRus() {
    return nameRus;
  }

  public String getNameEng() {
    return nameEng;
  }
}
