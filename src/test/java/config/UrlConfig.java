package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/url.properties"})

public interface UrlConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("loginUrl")
    String getLoginUrl();

    @Key("enrolUrl")
    String getEnrolUrl();

    @Key("coursesUrl")
    String getCoursesUrl();
}
