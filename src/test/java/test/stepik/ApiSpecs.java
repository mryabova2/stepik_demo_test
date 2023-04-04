package test.stepik;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.ApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ApiSpecs extends TestBase {
    public static RequestSpecification basicRequest =
            with()
                    .baseUri(stepikUrl)
                    .basePath("/api")
                    .filter(withCustomTemplates())
                    .log().all()
                    .contentType("application/json; charset=UTF-8")
                    .header("referer", stepikUrl + "/");

    public static ResponseSpecification okResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification createdResponse = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
    public static ResponseSpecification noContentResponse = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();
    public static ResponseSpecification noAuthResponse = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .build();

}

