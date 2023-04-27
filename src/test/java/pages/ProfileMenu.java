package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileMenu {

    //elements
    public SelenideElement profileHeader = $(" .profile-header-widget__info");

    //actions
    public ProfileMenu goToSettings() {
        $("[aria-label=Profile]").shouldBe(visible).click();
        $("[data-qa=menu-item-settings]").click();
        return this;
    }

    public ProfileMenu goToProfile() {
        $("[aria-label=Profile]").shouldBe(visible).click();
        $("[data-qa=menu-item-profile]").click();
        return this;
    }
}
