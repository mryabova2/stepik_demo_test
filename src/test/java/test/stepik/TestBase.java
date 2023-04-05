package test.stepik;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.AuthConfig;
import config.WebDriverConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestBase {

    static String stepikUrl = "https://stepik.org";
    static String loginUrl = "/users/login";
    static String enrolUrl = "/enrollments";
    static String coursesUrl = "/user-courses";
    int javaCourseId = 187;
    int sqlCourseId = 63054;
    String wrongAuthText = "The e-mail address and/or password you specified are not correct";
    String noXcsrfText = "CSRF token missing or incorrect";

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();

    static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    static Map<String, String> authBody = Map.of("email", config.getEmail(), "password", config.getPassword());

    @BeforeAll
    static void setProperties() {
        String properties = System.setProperty("launchType", "local");
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, ConfigFactory.getProperties());
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        if ((config.getLaunchType()).equals("remote")) {
            Configuration.remote = config.getRemoteUrl();
        }
    }

    @BeforeEach
    void configBaseUrl() {
        Configuration.baseUrl = stepikUrl;
    }

    @BeforeAll
    static void configAllureUi() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void configAllureAttachments(){
        Allure.getLifecycle().addAttachment(
                "Page Source",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );
    }
}
