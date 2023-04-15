package pages;

import annotations.Path;
import data.CoursesData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

  public MainPage(WebDriver driver) {
    super(driver);
  }

  private String searchCourseDate;
  private String courseNameLocator = "//div[contains(text(), '%s')]/..";
  @FindBy(css = ".lessons__new-item-bottom")
  private List<WebElement> startCourseSelector;
  @FindBy(css = ".cookies")
  private WebElement cookieElement;

  public CoursePage clickCourseByName(CoursesData coursesData) {
    WebElement courseSelector = driver.findElement(By.xpath(String.format(courseNameLocator, coursesData.getName())));
    clickCourseBy(courseSelector);

    return new CoursePage(driver);
  }

  public CoursePage clickCourseByDate(Boolean isEarly) {
    WebElement courseSelector = startCourseSelector.stream()
        .filter(el -> el.getText().contains(getCourseDate(isEarly))).collect(Collectors.toList()).get(0);
    clickCourseBy(courseSelector);

    return new CoursePage(driver, searchCourseDate);
  }

  public void clickCourseBy(WebElement element) {
    deleteCookiesWebElement(driver, cookieElement);
    actions.moveToElement(element).build().perform();
    Assertions.assertAll(
        () -> Assertions.assertTrue(waiter.elementShouldBeVisible(element)),
        () -> Assertions.assertTrue(waiter.attributeShouldBePresent(element, "transition"))
    );
    element.click();
  }

  public String getCourseDate(Boolean isEarly) {
    SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.ROOT);
    Pattern searchMonthPattern = Pattern.compile("(\\d{1,2}\\s(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря))");
    List<String> filtered = new ArrayList<>();

    for (WebElement element : startCourseSelector) {
      Matcher m = searchMonthPattern.matcher(element.getText());
      if (m.find()) {
        filtered.add(m.group(1));
      }
    }
    String courseDate = filtered.stream()
        .map(el -> el.replaceAll("(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря)", getMonth(el)))
        .map(el -> {
          try {
            return format.parse(el);
          } catch (ParseException e) {
            throw new RuntimeException(e);
          }
        })
        .sorted((s1, s2) -> {
          if (isEarly) {
            return s1.compareTo(s2);
          }
          return s2.compareTo(s1);
        })
        .map(date -> format.format(date))
        .collect(Collectors.toList()).get(0);

    return searchCourseDate = courseDate.replaceAll("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)", getMonth(courseDate));
  }

  public String getMonth(String text) {
    switch (text.replaceAll("\\d{1,2}\\s", "")) {
      case "января":
        return "January";
      case "Jan":
        return "января";
      case "февраля":
        return "February";
      case "Feb":
        return "февраля";
      case "марта":
        return "March";
      case "Mar":
        return "марта";
      case "апреля":
        return "April";
      case "Apr":
        return "апреля";
      case "мая":
        return "May";
      case "May":
        return "мая";
      case "июня":
        return "June";
      case "Jun":
        return "июня";
      case "июля":
        return "July";
      case "Jul":
        return "июля";
      case "августа":
        return "August";
      case "Aug":
        return "августа";
      case "сентября":
        return "September";
      case "Sep":
        return "сентября";
      case "октября":
        return "October";
      case "Oct":
        return "октября";
      case "ноября":
        return "November";
      case "Nov":
        return "ноября";
      case "декабря":
        return "December";
      case "Dec":
        return "декабря";
      default:
        return null;
    }
  }
}
