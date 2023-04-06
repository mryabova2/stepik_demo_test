package test.stepik;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UiTestBase extends TestBase{

    @BeforeAll
    static void configBaseUrl() {
        Configuration.baseUrl = stepikUrl;
    }

    @BeforeAll
    static void configAllureUi() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
        WebDriverRunner.closeWebDriver();
    }

    @AfterEach
    void addAttachements(){
        pageSource("Final Page Source");
        screenshotAs("Final Screenshot");
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String AttachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{attachName}", type = "text/html")
    public byte[] pageSource(String attachName) {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png", fileExtension = "png")
    public static byte[] screenshotAs(String attachName) {
        return (((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES));
    }

}
