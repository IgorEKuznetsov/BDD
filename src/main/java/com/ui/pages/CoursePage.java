package com.ui.pages;

import com.google.inject.Inject;
import com.ui.support.GuiceScoped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoursePage extends BasePageAbs<CoursePage> {
  private String date;

  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public CoursePage(GuiceScoped guiceScoped, String date) {
    super(guiceScoped);
    this.date = date;
  }

  private String startCourseLocator = "//*[contains(text(), '%s')]";

  public void checkDate() {
    String locator = String.format(startCourseLocator, date);
    String coursePageDateStart = driver.findElements(By.xpath(locator)).get(0).getText();
    Assertions.assertTrue(coursePageDateStart.contains(date));
    log.info("Start date: " + date);
  }
}
