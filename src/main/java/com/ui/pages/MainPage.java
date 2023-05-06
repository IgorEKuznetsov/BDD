package com.ui.pages;

import com.google.inject.Inject;
import com.ui.annotations.Path;
import com.ui.data.CoursesData;
import com.ui.data.MonthData;
import com.ui.support.GuiceScoped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Path("/")
public class MainPage extends BasePageAbs<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String searchCourseDate;


  private String courseNameLocator = "//div[contains(text(), '%s')]/..";
  @FindBy(css = ".lessons__new-item-bottom")
  private List<WebElement> startCourseSelector;
  @FindBy(css = ".cookies")
  private WebElement cookieElement;

  public CoursePage clickCourseByName(CoursesData coursesData) {
    WebElement courseSelector = guiceScoped.getDriver().findElement(By.xpath(String.format(courseNameLocator, coursesData.getName())));
    clickCourseBy(courseSelector);

    return new CoursePage(guiceScoped);
  }

  public void clickCourseByDate(String date) {
    WebElement courseSelector = startCourseSelector.stream()
        .filter(el -> {
          try {
            return el.getText().contains(getCourseDate(date));
          } catch (ParseException e) {
            throw new RuntimeException(e);
          }
        }).collect(Collectors.toList()).get(0);
    clickCourseBy(courseSelector);
    log.info("Поиск по дате: " + date);
    log.info("Результат: " + searchCourseDate);

  }

  public void clickCourseBy(WebElement element) {
    deleteNotifierWebElement(guiceScoped, cookieElement);
    actions.moveToElement(element).build().perform();
    Assertions.assertAll(
        () -> Assertions.assertTrue(waiter.elementShouldBeVisible(element)),
        () -> Assertions.assertTrue(waiter.attributeShouldBePresent(element, "transition"))
    );
    log.info("Курс: " + element.getText());
    element.click();
  }

  public String getCourseDate(String inputDate) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.ROOT);
    Pattern searchMonthPattern = Pattern.compile("(\\d{1,2}\\s(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря))");
    List<String> filtered = new ArrayList<>();

    for (WebElement element : startCourseSelector) {
      Matcher m = searchMonthPattern.matcher(element.getText());
      if (m.find()) {
        filtered.add(m.group(1));
      }
    }
    if (filtered.contains(inputDate)) {
      return searchCourseDate = inputDate;
    } else {
      Date parseDate = format.parse(inputDate.replaceAll("января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря", getMonth(inputDate)));
      String courseDate = filtered.stream()
          .map(el -> el.replaceAll("января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря", getMonth(el)))
          .map(el -> {
            try {
              return format.parse(el);
            } catch (ParseException e) {
              throw new RuntimeException(e);
            }
          })
          .filter(el -> el.after(parseDate))
          .sorted((s1, s2) -> s1.compareTo(s2))
          .map(el -> format.format(el))
          .collect(Collectors.toList()).get(0);

      return searchCourseDate = courseDate.replaceAll("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec", getMonth(courseDate));
    }
  }

  public String getMonth(String text) {
    switch (text.replaceAll("\\d{1,2}\\s", "")) {
      case "января":
        return MonthData.JANUARY.getNameEng();
      case "Jan":
        return MonthData.JANUARY.getNameRus();
      case "февраля":
        return MonthData.FEBRUARY.getNameEng();
      case "Feb":
        return MonthData.FEBRUARY.getNameRus();
      case "марта":
        return MonthData.MARCH.getNameEng();
      case "Mar":
        return MonthData.MARCH.getNameRus();
      case "апреля":
        return MonthData.APRIL.getNameEng();
      case "Apr":
        return MonthData.APRIL.getNameRus();
      case "мая":
        return MonthData.MAY.getNameEng();
      case "May":
        return MonthData.MAY.getNameRus();
      case "июня":
        return MonthData.JUNE.getNameEng();
      case "Jun":
        return  MonthData.JUNE.getNameRus();
      case "июля":
        return MonthData.JULY.getNameEng();
      case "Jul":
        return MonthData.JULY.getNameRus();
      case "августа":
        return MonthData.AUGUST.getNameEng();
      case "Aug":
        return MonthData.AUGUST.getNameRus();
      case "сентября":
        return MonthData.SEPTEMBER.getNameEng();
      case "Sep":
        return MonthData.SEPTEMBER.getNameRus();
      case "октября":
        return MonthData.OCTOBER.getNameEng();
      case "Oct":
        return MonthData.OCTOBER.getNameRus();
      case "ноября":
        return MonthData.NOVEMBER.getNameEng();
      case "Nov":
        return MonthData.NOVEMBER.getNameRus();
      case "декабря":
        return MonthData.DECEMBER.getNameEng();
      case "Dec":
        return MonthData.DECEMBER.getNameRus();
      default:
        return null;
    }
  }
}
