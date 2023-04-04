package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${launchType}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("launchType")
    String getLaunchType();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browserSize")
    @DefaultValue("")
    String getBrowserSize();

}
