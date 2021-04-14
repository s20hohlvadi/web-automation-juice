package com.testing.pageObjects.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/address/create")
public class AddressCreatePage extends AddressSelectPage {
  // Locators
  // -------------------------------------------------------------------------------------------------------------------
  public static By CREATE_AN_ADDRESS_TITLE = text("Add New Address");
  public static By COUNTRY_FIELD = id("mat-input-12");
  public static By NAME_FIELD = id("mat-input-13");
  public static By MOBILE_NUMBER_FIELD = id("mat-input-14");
  public static By ZIP_CODE_FIELD = id("mat-input-15");
  public static By ADDRESS_FIELD = id("address");
  public static By CITY_FIELD = id("mat-input-17");
  public static By STATE_FIELD = id("mat-input-18");
  public static By SUBMIT_BUTTON = id("submitButton");
  // Public methods
  // -------------------------------------------------------------------------------------------------------------------
  public void waitForPageToLoad(){
    getElement(CREATE_AN_ADDRESS_TITLE).waitUntilVisible();
    logWeAreOnPage();
  }

  public void click(String elementName){
    super.click(snakify(elementName));
    if(snakify(elementName).equals("SUBMIT_BUTTON")) getElement(SELECT_AN_ADDRESS_TITLE).waitUntilVisible();
  }
}
