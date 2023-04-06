package test.stepik;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
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

public class UiStepikTests extends UiTestBase {

    LoginForm loginForm = new LoginForm();
    ProfileInfo profileInfo = new ProfileInfo();
    ProfileMenu profileMenu = new ProfileMenu();
    Catalog catalog = new Catalog();

    @Test
    @Feature("Search")
    @DisplayName("Search course by key word and check output")
    void simpleSearchTest() {
        Allure.parameter("Key Word", "Java");

        step("Open courses catalog", () ->
                catalog.openCatalog());

        step("Input search key-word", () ->
                catalog.setSearchValue("Java"));

        step("Every course found contains key word", () -> {
            int totalCoursesFound = $$(Catalog.coursesFound).size();
            $$(Catalog.coursesFound).filterBy(text("Java")).shouldHave(size(totalCoursesFound));
        });
    }

    @Test
    @Feature("Profile info")
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
            screenshotAs("Filled in profile info");
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
}

