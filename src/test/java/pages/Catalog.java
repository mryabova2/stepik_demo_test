package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Catalog {

    //elements
    public SelenideElement courseTitle = $(" .learn-course-tile");
    public SelenideElement favoriteCourseMark = $(" .favorite-outline_icon");

    //actions
    public Catalog openMainPage() {
        open("");
        return this;
    }

    public Catalog setSearchValue(String value) {
        $(".search-form__input").shouldBe(visible).setValue(value);
        return this;
    }

    public Catalog submitSearch() {
        $(" .search-form__submit").click();
        return this;
    }

    public Catalog selectFilterFree() {
        $$(".form-checkbox").last().click();
        return this;
    }

    public Catalog checkLoaded() {
        $("[data-view=search-item]").shouldBe(visible);
        return this;
    }

    public int countCoursesFound() {
        return $$("[data-view=search-item]").size();
    }

    public int filterCoursesFound(String keyWord) {
       return $$("[data-view=search-item]").filterBy(text(keyWord)).size();
    }
}
