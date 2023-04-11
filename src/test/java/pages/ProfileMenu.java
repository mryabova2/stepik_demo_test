package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class ProfileMenu {
//elements
    public static String profileHeader = " .profile-header-widget__info";

//actions
    public ProfileMenu goToSettings () {
        $("[aria-label=Profile]").shouldBe(Condition.visible).click();
        $("[data-qa=menu-item-settings]").click();
        return this;
    }

    public ProfileMenu goToProfile(){
        $("[aria-label=Profile]").shouldBe(Condition.visible).click();
        $("[data-qa=menu-item-profile]").click();
        return this;
    }
}
