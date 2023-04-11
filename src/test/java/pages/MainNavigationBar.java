package pages;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class MainNavigationBar {

    public MainNavigationBar goToLearning (){
        $("[data-navbar-item=learn]").should(exist).click();
        return this;
    }

    public MainNavigationBar gotoCatalog(){
        $("[data-navbar-item=catalog]").should(exist).click();
        return this;
    }
}
