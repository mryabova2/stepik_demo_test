package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Catalog {

//elements
    public static String coursesFound = "[data-view=search-item]";
    public static String favoriteCorseMark = " .favorite-outline_icon";
    public static String courseTitle = " .learn-course-tile";

//actions
    public Catalog openMainPage(){
        open("");
        return this;
    }


    public Catalog setSearchValue(String value){
        $(".search-form__input").setValue(value);
        return this;
    }

    public Catalog submitSearch(){
        $(" .search-form__submit").click();
        return this;
    }

    public Catalog freeCheck(){
        $$(".form-checkbox").last().click();
        return this;
    }

    public Catalog checkLoaded (){
        $(coursesFound).shouldBe(visible);
        return this;
    }
}
