package pages;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Catalog {

    public static String coursesFound = "[data-view=search-item]";
    public static String favoriteCorseMark = " .favorite-outline_icon";
    public static String courseTitle = " .learn-course-tile";

    public Catalog openCatalog(){
        open("");
        return this;
    }

    public Catalog gotoCatalog(){
        $("[data-navbar-item=catalog]").should(exist).click();
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


    public Catalog markAsFavorite(){
        $(" .favorite-outline_icon").shouldBe(visible).click();
        return this;
    }

    public Catalog checkLoaded (){
        $(coursesFound).shouldBe(visible);
        return this;
    }
}
