package pages;

import test.stepik.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginForm extends TestBase {

     //actions
     public LoginForm openLoginMenu() {
          open("");
          $("#ember255").click();
          return this;
     }

     public LoginForm setEmail (String value){

          $("#id_login_email").setValue(value);
          return this;
     }

     public LoginForm setPassword (String value){
          $("#id_login_password").setValue(value);
          return this;
     }

     public LoginForm submitLoginForm (){
          $("[type=submit]").click();
          return this;
     }

}
