package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Catalog {

    public static String coursesFound = "[data-view=search-item]";

    public Catalog openCatalog(){
        open("");
        return this;
    }

    public Catalog setSearchValue(String value){
        $(".search-form__input").setValue(value).pressEnter();
        return this;
    }

    public Catalog checkLoaded (){
        $(coursesFound).shouldBe(visible);
        return this;
    }
}
