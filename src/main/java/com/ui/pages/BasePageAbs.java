package com.ui.pages;

import com.google.inject.Inject;
import com.ui.annotations.Path;
import com.ui.baseobject.BaseObj;
import com.ui.data.CoursesData;
import com.ui.support.GuiceScoped;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.annotation.Annotation;

public abstract class BasePageAbs<T> extends BaseObj<T> {
  @Inject
  public BasePageAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public T open() {
    driver.get(getBaseUrl() + getPath());
    return (T) this;
  }

  private String getPath() {
    Class<? extends BasePageAbs> clazz = getClass();
    Annotation annotation = clazz.getAnnotation(Path.class);
    if (annotation != null) {
      return ((Path) annotation).value();
    }
    return "";
  }

  private String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("driver.host"), "/");
  }

  private String courseLocator = "//div[contains(text(), '%s')]";
  private String specializationLocator = "//h1[contains(text(), '%s')]";


  public T headerIsEqualTo(CoursesData coursesData, Boolean isCourseLocator) {
    String locator = "";
    if (isCourseLocator) {
      locator = String.format(courseLocator, coursesData.getName());
    } else locator = String.format(specializationLocator, coursesData.getName());
    Assertions.assertEquals(coursesData.getName(), driver.findElements(By.xpath(locator)).get(0).getText());
    return (T) this;
  }

  public void deleteCookiesWebElement(WebDriver driver, WebElement element) {
    if (element.isDisplayed()) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", element);
      log.info("Cookie element was closed");
    } else log.info("Cookie element not visible");

  }


}
