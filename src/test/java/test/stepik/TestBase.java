package test.stepik;

import com.github.javafaker.Faker;
import config.AuthConfig;
import config.UrlConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public class TestBase {

    static UrlConfig configUrl = ConfigFactory.create(UrlConfig.class, System.getProperties());
    static AuthConfig configAuth = ConfigFactory.create(AuthConfig.class, System.getProperties());

    protected static String loginUrl = configUrl.getLoginUrl(),
            stepikUrl = configUrl.getBaseUrl(),
            enrolUrl = configUrl.getEnrolUrl(),
            coursesUrl = configUrl.getCoursesUrl();

    protected static Map<String, String> authBody = Map.of("email", configAuth.getEmail(), "password", configAuth.getPassword());

    int javaCourseId = 187,
            sqlCourseId = 63054;

    String wrongAuthText = "The e-mail address and/or password you specified are not correct",
            noXcsrfText = "CSRF token missing or incorrect";

    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName();

    String getRandomLanguage() {
        int r = (int) (Math.random() * 8);
        return new String[]{"беларуская", "Deutsch", "English", "español", "Português", "Русский", "Українська", "简体中文"}[r];
    }
}
