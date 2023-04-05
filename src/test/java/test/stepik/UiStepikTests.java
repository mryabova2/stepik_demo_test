package test.stepik;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.Catalog;
import pages.ProfileInfo;
import pages.ProfileMenu;
import pages.LoginForm;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class UiStepikTests extends TestBase {

    LoginForm loginForm = new LoginForm();
    ProfileInfo profileInfo = new ProfileInfo();
    ProfileMenu profileMenu = new ProfileMenu();
    Catalog catalog = new Catalog();


    @Test
    @DisplayName("Search course by key word and check output")
    void simpleSearchTest() {
        step("Open catalog", () ->
                catalog.openCatalog());

        step("Input search key-word", () ->
                catalog.setSearchValue("Java"));

        step("Check search results", () -> {
            int totalCoursesFound = $$(Catalog.coursesFound).size();
            $$(Catalog.coursesFound).filterBy(text("Java")).shouldHave(size(totalCoursesFound));
        });
    }

    @Test
    @DisplayName("Change user-profile info")
    void changeProfileInfo() {
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

        step("Update profile info", () -> {
            profileInfo
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setLanguage("English")
                    .uploadPicture("testImage.jpg")
                    .submitProfileInfo()
                    .confirmChanges();
        });

        step("Check updated profile info", () -> {
            profileMenu.goToProfile();
            $(ProfileMenu.profileHeader).shouldHave(text(firstName + " " + lastName));
        });
    }
}
