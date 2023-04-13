package test.stepik;

import attach.AllureAttach;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class UiStepikTests extends UiTestBase {

    LoginForm loginForm = new LoginForm();
    ProfileInfo profileInfo = new ProfileInfo();
    ProfileMenu profileMenu = new ProfileMenu();
    Catalog catalog = new Catalog();
    MainNavigationBar navigationBar = new MainNavigationBar();
    Wishlist wishlist = new Wishlist();

    @Test
    @Epic("UI")
    @Feature("Search")
    @AllureId("17196")
    @DisplayName("Search course by key word and check output")
    void simpleSearchTest() {
        Allure.parameter("Key Word", "Java");

        step("Open courses catalog", () ->
                catalog.openMainPage());

        step("Input search key-word", () ->
                catalog.setSearchValue("Java")
                        .checkLoaded()
                        .submitSearch());

        step("Every course found contains key word", () -> {
            int totalCoursesFound = $$(Catalog.coursesFound).size();
            $$(Catalog.coursesFound).filterBy(text("Java")).shouldHave(size(totalCoursesFound));
        });
    }

    @Test
    @Epic("UI")
    @Feature("Profile info")
    @AllureId("17197")
    @DisplayName("Change user-profile info")
    void changeProfileInfo() {
        Allure.parameter("Name", firstName);
        Allure.parameter("Last Name", lastName);
        Allure.parameter("Language", getRandomLanguage());

        step("Login with existing user", () -> {
            loginForm
                    .openLoginMenu()
                    .setEmail(authBody.get("email"))
                    .setPassword(authBody.get("password"))
                    .submitLoginForm();
        });

        step("Go to profile info blank", () -> {
            profileMenu.goToSettings();
        });

        step("Input random name, last name, language", () -> {
            profileInfo
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setLanguage(getRandomLanguage());
            AllureAttach.screenshotAs("Filled in profile info");
        });

        step("Confirm updated information", () -> {
            profileMenu.goToProfile();
            profileInfo.confirmChanges();
            profileMenu.goToProfile();
        });
        step("Profile header is updated", () -> (
                $(ProfileMenu.profileHeader).shouldHave(text(firstName + " " + lastName)))
        );
    }

    @Test
    @Epic("UI")
    @Feature("Search")
    @AllureId("17198")
    @DisplayName("Search course with filter Free")
    void filterSearch() {
        step("Open courses catalog", () ->
                catalog.openMainPage());

        step("Input search key-word", () ->
                catalog.setSearchValue("Java"));

        step("Set filter Free of charge", () ->
                catalog.freeCheck()
                        .submitSearch());
        step("Every course found is free of charge", () -> {
            int totalCoursesFound = $$(Catalog.coursesFound).size();
            $$(Catalog.coursesFound).filterBy(text("Бесплатно")).shouldHave(size(totalCoursesFound));
        });
    }

    @Test
    @Epic("UI")
    @Feature("Search")
    @AllureId("17195")
    @DisplayName("Add in wishlist and delete")
    void addInWishlist() {

        String courseName = "Python: разработка с нуля";
        Allure.parameter("Coerse name", courseName);

        step("Login with existing user", () -> {
            loginForm
                    .openLoginMenu()
                    .setEmail(authBody.get("email"))
                    .setPassword(authBody.get("password"))
                    .submitLoginForm()
                    .checkClosed();
        });

        step("Search course name", () ->
                catalog.setSearchValue(courseName)
                        .submitSearch());

        step("Mark course with like", () -> {
            catalog.checkLoaded();
            $(Catalog.favoriteCorseMark).click();
        });

        step("Marked course is listed in Wishlist", () -> {
            navigationBar.goToLearning();
            wishlist.goToWishList();
            $(Catalog.courseTitle).shouldHave(text(courseName));
        });

        step("Remove added course from Wishlist and confirm", () -> {
            wishlist.courseMoreInfo()
                    .removeFromWishlist()
                    .confirmChanges()
                    .checkConfirmed();
        });
    }
}


