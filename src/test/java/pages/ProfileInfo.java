package pages;


import com.codeborne.selenide.Condition;
import test.stepik.TestBase;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfileInfo  extends TestBase{

    public ProfileInfo setFirstName (String value){
        $("#first_name").setValue(value);
        return this;
    }

    public ProfileInfo setLastName (String value){
        $("#last_name").setValue(value);
        return this;
    }

    public ProfileInfo setLanguage(String value){
        $("#supported_languages").selectOption("English");
        return this;
    }

    public ProfileInfo uploadPicture (String imgPath){
        $(" .file-upload__input").uploadFromClasspath(imgPath);
        return this;
    }

    public ProfileInfo submitProfileInfo(){
        $(byText("Save changes")).click();
        return this;
    }

    public ProfileInfo confirmChanges(){
        if ($(" .modal-dialog-inner").is(Condition.visible)) {
            $(" .$( .modal-dialog-confirm__buttons").$(byText("Save")).click();
        }
        return this;
    }
}
