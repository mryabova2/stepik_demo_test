package pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfileMenu {

    public static String profileHeader = " .profile-header-widget__info";

    public ProfileMenu goToSettings () {
        $(" .navbar__profile-toggler").click();
        $(" .drop-down__body").$(byText("Settings")).click();
        return this;
    }

    public ProfileMenu goToProfile(){
        $(" .navbar__profile-toggler").click();
        $(" .drop-down__body").$(byText("Profile")).click();
        return this;
    }
}
