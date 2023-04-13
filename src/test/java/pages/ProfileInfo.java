package pages;

import test.stepik.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileInfo extends TestBase {

    public ProfileInfo setFirstName(String value) {
        $("#first_name").setValue(value);
        return this;
    }

    public ProfileInfo setLastName(String value) {
        $("#last_name").setValue(value);
        return this;
    }

    public ProfileInfo setLanguage(String value) {
        $("#supported_languages").selectOption(value);
        return this;
    }

    public ProfileInfo confirmChanges() {
        $(" .modal-dialog-confirm__buttons").shouldBe(visible);
        $(" .modal-dialog-confirm__buttons [type=button]").click();
        return this;
    }
}
