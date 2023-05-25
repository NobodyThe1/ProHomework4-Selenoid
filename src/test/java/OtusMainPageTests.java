import components.CourseTile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.AnyPage;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URI;

public class OtusMainPageTests {

    private WebDriver driver;

    @BeforeAll
    public void init() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser.name", "chrome"));
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", "112.0"));
        capabilities.setCapability("enableVNC", true);
        driver = new RemoteWebDriver(
                URI.create("127.0.0.1:8080/wd/hub").toURL(),
                capabilities
        );
    }

    @AfterEach
    public void close() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void moveToCourseTile() {
        new MainPage(driver)
                .open();
        new CourseTile(driver)
                .moveToCourse("Специализация Administrator Linux");
    }

    @Test
    public void filterCourseByTitle() {
        new MainPage(driver)
                .open();
        new CourseTile(driver)
                .findCourseByTitle("Специализация Administrator Linux");
        new AnyPage(driver)
                .rightPageShouldBeOpened("Специализация Administrator Linux");
    }

    @Test
    public void getCourseDate() {
        new MainPage(driver)
                .open();
        new CourseTile(driver)
                .getEarlierLaterCourse(true);
    }
}