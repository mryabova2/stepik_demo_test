package test.stepik;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static test.stepik.ApiSpecs.basicRequest;
import static test.stepik.ApiSpecs.noContentResponse;

public class AuthCookies extends TestBase {


// gets csrf-token and session-id before authorization
    static Map<String, String> preAuth =
            given()
                    .spec(basicRequest)
                    .when()
                    .get(loginUrl)
                    .then()
                    .log().all()
                    .extract().cookies();

// gets csrf-token and session-id after authorization
    static Map<String, String> postAuth =
            given()
                    .spec(basicRequest)
                    .body(authBody)
                    .cookies(preAuth)
                    .header("x-csrftoken", preAuth.get("csrftoken"))
                    .when()
                    .post(loginUrl)
                    .then()
                    .log().all()
                    .spec(noContentResponse)
                    .extract().cookies();


}
