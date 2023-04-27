package test.stepik;

import attach.AllureAttach;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiStepikTests extends UiTestBase {

    @Test
    @Epic("UI")
    @Feature("Search")
    @AllureId("17196")
    @DisplayName("Search course by key word and check output")
    void simpleSearchTest() {
        String keyWord = "Java";
        Allure.parameter("Key Word", keyWord);

        step("Open courses catalog", () ->
                catalog.openMainPage());

        step("Input search key-word", () ->
                catalog.setSearchValue(keyWord)
                        .submitSearch());

        step("Every course found contains key word", () -> {
            assertEquals(catalog.filterCoursesFound(keyWord), catalog.countCoursesFound());
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
                    .fillAuthInfo(authEmail, authPassword)
                    .checkClosed();
        });

        step("Go to profile settings", () -> {
            profileMenu.goToSettings();
        });

        step("Input random name, last name, language", () -> {
            profileInfo
                    .setRandomUserInfo(firstName, lastName, getRandomLanguage());
            AllureAttach.screenshotAs("Filled in profile info");
        });

        step("Confirm updated information", () -> {
            profileMenu.goToProfile();
            profileInfo.confirmChanges();
            profileMenu.goToProfile();
        });

        step("Profile header is updated", () -> (
                profileMenu.profileHeader.shouldHave(text(firstName + " " + lastName)))
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
                catalog.selectFilterFree()
                        .submitSearch());

        step("Every course found is free of charge", () -> {
            assertEquals(catalog.filterCoursesFound("Бесплатно"), catalog.countCoursesFound());
        });
    }

    @Test
    @Epic("UI")
    @Feature("Search")
    @AllureId("17195")
    @DisplayName("Add in wishlist and delete")
    void addInWishlist() {

        String courseName = "Python: разработка с нуля";
        Allure.parameter("Course name", courseName);

        step("Login with existing user", () -> {
            loginForm
                    .fillAuthInfo(authEmail, authPassword)
                    .checkClosed();
        });

        step("Search course name", () ->
                catalog.setSearchValue(courseName)
                        .submitSearch());

        step("Mark course with like", () -> {
            catalog.checkLoaded();
            catalog.favoriteCourseMark.click();
        });

        step("Marked course is listed in Wishlist", () -> {
            navigationBar.goToLearning();
            wishlist.goToWishList();
            catalog.courseTitle.shouldHave(text(courseName));
        });

        step("Remove added course from Wishlist and confirm", () -> {
            wishlist.courseMoreInfo()
                    .removeFromWishlist()
                    .confirmChanges()
                    .checkConfirmed();
        });
    }
}