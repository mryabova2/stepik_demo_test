package test.stepik;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static test.stepik.ApiSpecs.*;


public class ApiStepikTests extends TestBase {


    @Test
    @DisplayName("Authorization without password")
    void wrongPasswordAuth() {
        Map<String, String> preAuthCookies =
                given()
                        .spec(basicRequest)
                        .when()
                        .get(loginUrl)
                        .then()
                        .log().all()
                        .extract().cookies();

        given()
                .spec(basicRequest)
                .body("")
                .cookies(preAuthCookies)
                .header("x-csrftoken", preAuthCookies.get("csrftoken"))
                .when()
                .post(loginUrl)
                .then()
                .spec(noAuthResponse)
                .log().all()
                .body(containsString(wrongAuthText));
    }

    @Test
    @DisplayName("Authorization without xcsrf-token")
    void noXcsrfAuth() {
        Map<String, String> preAuthCookies =
                given()
                        .spec(basicRequest)
                        .when()
                        .get(loginUrl)
                        .then()
                        .log().all()
                        .extract().cookies();

        given()
                .spec(basicRequest)
                .body(authBody)
                .cookies(preAuthCookies)
                .when()
                .post(loginUrl)
                .then()
                .log().all()
                .spec(noAuthResponse)
                .body(containsString(noXcsrfText));
    }

    @Test
    @DisplayName("Enroll in course")
    void enrollInCourse() {
        given()
                .spec(basicRequest)
                .body("{\"enrollment\":{\"course\":\"63054\"}}")
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .when()
                .post(enrolUrl)
                .then()
                .spec(createdResponse)
                .log().all();
    }

    @Test
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
    @DisplayName("Enroll and leave course")
    void leaveCourse() {
        given()
                .spec(basicRequest)
                .body("{\"enrollment\":{\"course\":\"63054\"}}")
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .when()
                .post(enrolUrl)
                .then()
                .spec(createdResponse)
                .log().all();

        given()
                .spec(basicRequest)
                .when()
                .cookies(AuthCookies.postAuth)
                .header("x-csrftoken", AuthCookies.postAuth.get("csrftoken"))
                .delete(enrolUrl + "/" + sqlCourseId)
                .then()
                .spec(noContentResponse)
                .log().all();
    }
}


