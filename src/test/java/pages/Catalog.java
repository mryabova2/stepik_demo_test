package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Catalog {

    public static String allCorsesLinks = ".course-card__title[href]";

    public Catalog openCatalog(){
        open("");
        return this;
    }

    public Catalog setSearchValue(String value){
        $(".search-form__input").setValue(value).pressEnter();
        return this;
    }
}
