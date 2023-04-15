package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


public class OperaWebDriver implements IDriver {
  @Override
  public WebDriver getDriver() {
    WebDriverManager.operadriver().setup();
    return null;
  }
}
