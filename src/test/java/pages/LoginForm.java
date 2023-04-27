package pages;

import test.stepik.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;



public class LoginForm extends TestBase {

     //actions
     public LoginForm fillAuthInfo(String email, String password) {
          open("/catalog?auth=login");
          $("#id_login_email").setValue(email);
          $("#id_login_password").setValue(password);
          $("[type=submit]").click();
          return this;
     }

     public void checkClosed() {
          $(".auth-widget").should(not(exist));
     }
}