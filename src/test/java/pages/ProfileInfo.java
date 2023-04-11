package pages;


import com.codeborne.selenide.Condition;
import test.stepik.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
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

    public ProfileInfo uploadPicture(String imgPath) {
        $(" .file-upload__input").uploadFromClasspath(imgPath);
        return this;
    }


    public ProfileInfo confirmChanges() {
        $(" .modal-dialog-confirm__buttons").shouldBe(visible);
        if ($(" .modal-dialog-confirm__buttons").$(byText("Сохранить")).is(visible)) {
            $(" .modal-dialog-confirm__buttons").$(byText("Сохранить")).click();
        } else {
            $(" .modal-dialog-confirm__buttons").$(byText("Save")).click();
        }
        return this;
    }
}
