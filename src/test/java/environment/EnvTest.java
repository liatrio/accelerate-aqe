package environment;
import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.*;

public class EnvTest{

    @Test
    public void CreateWebDriverTest(){
        System.setProperty("Headless", "true");
        WebDriver test = Env.CreateWebDriver("chrome");
        test.get("http://www.google.com/");
        WebElement element = test.findElement(By.id("searchform"));
        assertTrue(element.isDisplayed());

        test.close();
    }
}
