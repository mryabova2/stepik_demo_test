package pages;

import test.stepik.TestBase;

import static com.codeborne.selenide.Selenide.*;



public class LoginForm extends TestBase {

     //actions
     public LoginForm openLoginMenu() {
          open("/catalog?auth=login");
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
