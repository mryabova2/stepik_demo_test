package pages;

import static com.codeborne.selenide.Selenide.$;

public class MainNavigationBar {

    public MainNavigationBar goToLearning (){
        $("[data-navbar-item=learn]").click();
        return this;
    }
}
