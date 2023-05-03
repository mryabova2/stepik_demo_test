package test.stepik;

import api.AuthCookies;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.EnrollBody;
import pojo.Enrollment;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static api.ApiSpecs.*;


public class ApiStepikTests extends TestBase {


    @Test
    @Epic("API")
    @Feature("Log in")
    @AllureId("17194")
    @DisplayName("Authorization with wrong password should fail")
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

        step("Send body with correct cookies and wrong password", () ->
                given()
                .spec(basicRequest)
                .body(Map.of("email", authEmail, "password", wrongPassword))
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
    @Epic("API")
    @Feature("Log in")
    @AllureId("17190")
    @DisplayName("Authorization without xcsrf-token should fail")
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
    @Epic("API")
    @Feature("Enrollment")
    @AllureId("17193")
    @DisplayName("Enrolled course is in user course list")
    void checkCourseEnrolled() {

        Enrollment enrollment = new Enrollment(javaCourseId);
        EnrollBody enrollBody1 = new EnrollBody(enrollment);

        step("Enroll on course", () ->
                    given()
                            .spec(basicRequest)
                            .body(enrollBody1)
                            .cookies(AuthCookies.postAuth)
                            .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                            .when()
                            .post(enrolUrl)
                            .then()
                            .spec(createdResponse)
                            .log().all());

        step("Course is in the list of user courses", () ->
        given()
                .spec(basicRequest)
                .cookies(AuthCookies.postAuth)
                .when()
                .get(coursesUrl)
                .then()
                .log().body()
                .spec(okResponse)
                .body("user-courses.findAll{it.course}.course.flatten()",
                        hasItem(javaCourseId)));
    }

    @Test
    @Epic("API")
    @Feature("Drop course")
    @AllureId("17191")
    @DisplayName("Enrolled course is and deleted")
    void leaveCourse() {
        Enrollment enrollment = new Enrollment(sqlCourseId);
        EnrollBody enrollBody1 = new EnrollBody(enrollment);

        step("Send enrollment body with authorized user cookies", () ->
        given()
                .spec(basicRequest)
                .body(enrollBody1)
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


