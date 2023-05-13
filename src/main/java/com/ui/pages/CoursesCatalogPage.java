package com.ui.pages;

import com.google.inject.Inject;
import com.ui.annotations.Urls;
import com.ui.annotations.Path;
import com.ui.annotations.UrlTemplate;
import com.ui.data.PreparatoryCoursesData;
import com.ui.support.GuiceScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/catalog/courses")
@Urls(
    @UrlTemplate(name = "categories", value = "?categories=%s")
)
public class CoursesCatalogPage extends BasePageAbs<CoursesCatalogPage> {
  @Inject
  public CoursesCatalogPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(xpath = "//p[contains(text(), 'Подготовительный курс')]")
  List<WebElement> preparatoryCourses;

  @FindBy(xpath = "//button[contains(text(), 'Показать еще')]")
  WebElement showMoreButton;

  @FindBy(tagName = "h5")
  WebElement additionalInfo;

  @FindBy(css = "#__next > div[class] > div:first-child")
  WebElement notifier;

  @FindBy(css = "#__next > div[class] > div:nth-child(2) > div >div:first-child + section")
  WebElement section;


  public void searchPreparatoryCourseElements() {
    waiter.elementShouldBeVisible(section);
    waiter.elementShouldBeVisible(additionalInfo);
    while (true) {
      ((JavascriptExecutor) guiceScoped.getDriver()).executeScript("arguments[0].scrollIntoView(true);", additionalInfo);
      deleteNotifierWebElement(guiceScoped, notifier);
      log.info("Всего курсов: " + preparatoryCourses.size());
      if (preparatoryCourses.size() == 0) {
        waiter.elementShouldBeVisible(showMoreButton);
        showMoreButton.click();
        waiter.elementShouldBeVisible(section);
        waiter.elementShouldBeVisible(additionalInfo);
      } else break;
    }
    log.info(preparatoryCourses.get(0).getText() + " найден");
  }

  public void filterCourseByPrice(String filter) throws IOException {
    Document doc;
    List<Document> prices = new ArrayList<>();
    String baseUrl = getBaseUrl();

    List<String> preparatoryCoursesList = Arrays.asList(
        PreparatoryCoursesData.JAVA.getName(),
        PreparatoryCoursesData.PHP.getName(),
        PreparatoryCoursesData.JS.getName(),
        PreparatoryCoursesData.CSHARP.getName(),
        PreparatoryCoursesData.CPP.getName(),
        PreparatoryCoursesData.PROF_IT.getName());

    for (String course : preparatoryCoursesList) {
      doc = Jsoup.connect(baseUrl + "/" + course).get();
      prices.add(doc);
    }

    String price = prices.stream()
        .map(el -> el.selectXpath("//div[contains(text(), 'Стоимость')]/following-sibling::div"))
        .map(el -> el.text().replaceAll("\\s₽", ""))
        .map(el -> Integer.valueOf(el))
        .sorted((s1, s2) -> {
          if (filter.contains("Самый дорогой")) {
            return s2 - s1;
          }
          if (filter.contains("Самый дешевый")) {
            return s1 - s2;
          }
          return 0;
        })
        .collect(Collectors.toList()).get(0).toString();

    log.info("Цена: " + price + " руб.");

    prices.stream()
        .filter(el -> el.selectXpath("//div[contains(text(), 'Стоимость')]/following-sibling::div").text().contains(price))
        .map(el -> el.text())
        .forEach(log::info);


  }


}
