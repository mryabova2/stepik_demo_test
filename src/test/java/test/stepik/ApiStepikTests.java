package test.stepik;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static test.stepik.ApiSpecs.*;


public class ApiStepikTests extends TestBase {


    @Test
    @Feature("API")
    @DisplayName("Authorization with wrong password")
    void wrongPasswordAuth() {
        String wrongPassword = "12345";
        Allure.parameter("Password", wrongPassword);

        Map<String, String> preAuthCookies =
                given()
                        .spec(cookiesRequest)
                        .when()
                        .get(loginUrl)
                        .then()
                        .log().all()
                        .extract().cookies();

        step("Send body with correct cookies and wrong password", () ->    given()
                .spec(basicRequest)
                .body(Map.of("email", configAuth.getEmail(), "password", wrongPassword))
                .cookies(preAuthCookies)
                .header("x-csrftoken", preAuthCookies.get("csrftoken"))
                .when()
                .post(loginUrl)
                .then()
                .spec(noAuthResponse)
                .log().all()
                .body(containsString(wrongAuthText)));
    }

    @Test
    @Feature("API")
    @DisplayName("Authorization without xcsrf-token")
    void noXcsrfAuth() {
        Map<String, String> preAuthCookies =
                given()
                        .spec(cookiesRequest)
                        .when()
                        .get(loginUrl)
                        .then()
                        .log().all()
                        .extract().cookies();

        step("Send body with correct auth but without xcsrf header", () ->
                given()
                .spec(basicRequest)
                .body(authBody)
                .cookies(preAuthCookies)
                .when()
                .post(loginUrl)
                .then()
                .log().all()
                .spec(noAuthResponse)
                .body(containsString(noXcsrfText)));
    }

    @Test
    @Feature("API")
    @DisplayName("Enrollment in course")
    void enrollInCourse() {
        String enrollBody = "{\"enrollment\":{\"course\":\"63054\"}}";

        given()
                .spec(basicRequest)
                .body(enrollBody)
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .when()
                .post(enrolUrl)
                .then()
                .spec(createdResponse)
                .log().all();
    }

    @Test
    @Feature("API")
    @DisplayName("Enrolled course is in user course list")
    void checkActiveCourses() {

        given()
                .spec(basicRequest)
                .cookies(AuthCookies.postAuth)
                .when()
                .get(coursesUrl)
                .then()
                .log().body()
                .spec(okResponse)
                .body("user-courses.findAll{it.course}.course.flatten()",
                        hasItem(javaCourseId));
    }

    @Test
    @Feature("API")
    @DisplayName("Enrollment and deleting enrolled course")
    void leaveCourse() {
        String enrollBody = "{\"enrollment\":{\"course\":\"63054\"}}";

        step("Send enrollment body with authorized user cookies", () ->
        given()
                .spec(basicRequest)
                .body(enrollBody)
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .when()
                .post(enrolUrl)
                .then()
                .spec(createdResponse)
                .log().all());

        step("Delete by enrollment body with authorized user cookies", () ->
        given()
                .spec(basicRequest)
                .when()
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .delete(enrolUrl + "/" + sqlCourseId)
                .then()
                .spec(noContentResponse)
                .log().all());
    }
}


