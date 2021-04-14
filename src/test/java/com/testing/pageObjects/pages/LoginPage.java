package com.testing.pageObjects.pages;

import java.util.Map;

import io.cucumber.datatable.DataTable;

import org.openqa.selenium.By;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/login")
public class LoginPage extends HomePage {
  // Locators ----------------------------------------------------------------------------------------------------------
  public static By LOGIN_TITLE = text("Login");
  public static By LOGIN_WITH_GOOGLE_BUTTON = css("button[id='loginButtonGoogle']");
  public static By NOT_YET_A_CUSTOMER = css("a[class='primary-link']");
  public static By REGISTRATION_COMPLETED_SUCCESSFULLY = text("Registration completed successfully");
  public static By EMAIL_FIELD = id("email");
  public static By PASSWORD_FIELD = css("input[id='password']");
  public static By LOGIN_BUTTON = css("button[id='loginButton']");
  
  // Public methods ----------------------------------------------------------------------------------------------------
  public void waitForPageToLoad() {
    getElement(LOGIN_WITH_GOOGLE_BUTTON).waitUntilVisible();
    logWeAreOnPage();
  }

  public void performLogIn(DataTable data){
    for (Map<String,String> map : dataToMap(data)) {

      if(map.get("Email").contains("RANDOM_EMAIL")){
        getElement(EMAIL_FIELD).sendKeys((String) sessionVariableCalled("RANDOM_EMAIL"));
      } else {
        getElement(EMAIL_FIELD).sendKeys(map.get("Email"));
      }

      getElement(PASSWORD_FIELD).sendKeys(map.get("Password"));
      getElement(LOGIN_BUTTON).click();
      getElement(ALL_PRODUCTS_TITLE).waitUntilVisible();
    }
  }

  public void performCreateAnAccount(DataTable data){
    info("Creating an account, while on: " + this.getClass().getSimpleName());
    getElement(NOT_YET_A_CUSTOMER).click();
    RegisterPage page = new RegisterPage();
    page.waitForPageToLoad();
    page.performCreateAnAccount(data);
  }
}
