package com.ui.waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaiter {

  private WebDriver driver;
  long implicitlyWaitSecond = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", "5000")) / 1000;

  public CustomWaiter(WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    WebDriverWait wait = new WebDriverWait(driver, implicitlyWaitSecond);
    try {
      wait.until(condition);
      return true;
    } catch (TimeoutException ex) {
      ex.printStackTrace();
    }
    return false;
  }

  public boolean elementShouldBeVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }

  public boolean attributeShouldBePresent(WebElement element, String attr) {
    return waitForCondition(ExpectedConditions.attributeToBeNotEmpty(element, attr));
  }

}
