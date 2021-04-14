package com.testing.pageObjects.pages;

import java.util.Map;

import io.cucumber.datatable.DataTable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/basket")
public class BasketPage extends HomePage {
  // Locators
  // -------------------------------------------------------------------------------------------------------------------
  public static By YOUR_BASKET_TITLE = text("Your Basket");
  public static By ROW_ITEMS = css("mat-table[class='mat-table cdk-table']");
  public static By CHECKOUT_BUTTON = id("checkoutButton");
  // Public methods
  // -------------------------------------------------------------------------------------------------------------------
  public void waitForPageToLoad(){
    getElement(YOUR_BASKET_TITLE).waitUntilVisible();
    logWeAreOnPage();
  }

  public void click(String elementName){
    if(snakify(elementName).contains("CHECKOUT_BUTTON")) scrollIntoView(elementName);
    super.click(elementName);
  }
  
  public Boolean validateBasketContent(DataTable data) {
    Boolean found;
    String item ;

    for (Map<String, String> map : dataToMap(data)) {
      found = false;
      item = map.get("Item");

      for (WebElement element : getElements(ROW_ITEMS)) {
        if(!element.getText().contains(item)) continue;
        found = true;
        break;
      }

      if(found){
        logValidationPassed(item);
      } else {
        logValidationFailed(item);
        return false;
      }
    }
    return true;
  }
}
