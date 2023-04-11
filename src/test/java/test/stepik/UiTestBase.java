package test.stepik;

import attach.AllureAttach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UiTestBase extends TestBase {

    @BeforeAll
    static void setLaunchProperties() {
        System.setProperty("launchType", "remote");
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, ConfigFactory.getProperties());
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = 10000;
        if ((config.getLaunchType()).equals("remote")) {
            Configuration.remote = config.getRemoteUrl();
        }
    }

    @BeforeAll
    static void configBaseUrl() {
        Configuration.baseUrl = stepikUrl;
    }

    @BeforeAll
    static void configAllureUi() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void addAttachments() {
        AllureAttach.pageSource("Final Page Source");
        AllureAttach.screenshotAs("Final Screenshot");
        AllureAttach.addVideo();
        Selenide.closeWebDriver();
    }
}
