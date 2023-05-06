package com.ui.waiters;

import com.google.inject.Inject;
import com.ui.support.GuiceScoped;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaiter {
  @Inject
  protected GuiceScoped guiceScoped;

  long implicitlyWaitSecond = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", "5000")) / 1000;

  @Inject
  public CustomWaiter(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    WebDriverWait wait = new WebDriverWait(this.guiceScoped.driver, implicitlyWaitSecond);
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
