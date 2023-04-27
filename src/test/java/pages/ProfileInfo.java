package pages;

import test.stepik.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileInfo extends TestBase {

    public ProfileInfo setRandomUserInfo(String firstName, String lastName, String language) {
        $("#first_name").setValue(firstName);
        $("#last_name").setValue(lastName);
        $("#supported_languages").selectOption(language);
        return this;
    }

    public ProfileInfo confirmChanges() {
        $(" .modal-dialog-confirm__buttons").shouldBe(visible);
        $(" .modal-dialog-confirm__buttons [type=button]").click();
        return this;
    }
}
