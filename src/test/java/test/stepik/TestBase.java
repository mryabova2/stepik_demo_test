package test.stepik;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import config.AuthConfig;
import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class TestBase {
    Faker faker = new Faker();
    static String stepikUrl = "https://stepik.org",
            loginUrl = "/users/login",
            enrolUrl = "/enrollments",
            coursesUrl = "/user-courses";
    int javaCourseId = 187,
            sqlCourseId = 63054;
    String wrongAuthText = "The e-mail address and/or password you specified are not correct",
            noXcsrfText = "CSRF token missing or incorrect",
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName();

    String getRandomLanguage(){
        int r = (int) (Math.random()*8);
        return new String[] {"беларуская", "Deutsch", "English", "español", "Português", "Русский", "Українська", "简体中文" } [r];
    }

    static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    static Map<String, String> authBody = Map.of("email", config.getEmail(), "password", config.getPassword());

    @BeforeAll
    static void setLaunchProperties() {
        System.setProperty("launchType", "local");
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, ConfigFactory.getProperties());
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = 10000;
        if ((config.getLaunchType()).equals("remote")) {
            Configuration.remote = config.getRemoteUrl();
        }
    }
}
