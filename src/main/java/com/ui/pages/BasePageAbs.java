package com.ui.pages;

import com.google.inject.Inject;
import com.ui.annotations.Urls;
import com.ui.annotations.Path;
import com.ui.annotations.UrlTemplate;
import com.ui.baseobject.BaseObj;
import com.ui.data.CoursesData;
import com.ui.support.GuiceScoped;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.lang.annotation.Annotation;

public abstract class BasePageAbs<T> extends BaseObj<T> {
  @Inject
  public BasePageAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String getPathFromTemplate(String categoryName) {
    Class<? extends BasePageAbs> clazz = getClass();
    Urls urls = clazz.getAnnotation(Urls.class);
    UrlTemplate urlTemplate = urls.value();
    if (urlTemplate != null) {
      String category = String.format(urlTemplate.value(), categoryName);
      log.info("Category: " + category);
      return getPath() + category;
    }
    return "";
  }

  public void open() {
    guiceScoped.getDriver().get(getBaseUrl() + getPath());
  }

  public void open(String categoryName) {
    guiceScoped.getDriver().get(getBaseUrl() + getPathFromTemplate(categoryName));
  }

  private String getPath() {
    Class<? extends BasePageAbs> clazz = getClass();
    Annotation annotation = clazz.getAnnotation(Path.class);
    if (annotation != null) {
      return ((Path) annotation).value();
    }
    return "";
  }

  public String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("driver.host", "https://otus.ru"), "/");
  }

  private String courseLocator = "//div[contains(text(), '%s')]";
  private String specializationLocator = "//h1[contains(text(), '%s')]";


  public T headerIsEqualTo(CoursesData coursesData, Boolean isCourseLocator) {
    String locator = "";
    if (isCourseLocator) {
      locator = String.format(courseLocator, coursesData.getName());
    } else locator = String.format(specializationLocator, coursesData.getName());
    Assertions.assertEquals(coursesData.getName(), guiceScoped.getDriver().findElements(By.xpath(locator)).get(0).getText());
    return (T) this;
  }

  public void deleteNotifierWebElement(GuiceScoped guiceScoped, WebElement element) {
    if (element.isDisplayed()) {
      ((JavascriptExecutor) guiceScoped.getDriver()).executeScript("arguments[0].style.display='none'", element);
      log.info("Notifier element was closed");
    } else log.info("Notifier element not visible");
  }

}
