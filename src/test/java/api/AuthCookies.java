package api;

import test.stepik.TestBase;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static api.ApiSpecs.*;

public class AuthCookies extends TestBase {

// gets csrf-token and session-id before authorization
    public static Map<String, String> preAuth =
            given()
                    .spec(cookiesRequest)
                    .when()
                    .get(loginUrl)
                    .then()
                    .log().all()
                    .extract().cookies();

// gets csrf-token and session-id after authorization
    public static Map<String, String> postAuth =
            given()
                    .spec(cookiesRequest)
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
