package com.testing.pageObjects.pages;

import io.cucumber.datatable.DataTable;

import java.util.Map;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/search")
public class SearchResultsPage extends HomePage {
  // Locators
  // -------------------------------------------------------------------------------------------------------------------
  public static By SEARCH_RESULTS_TITLE = id("search-result-heading");
  public static By SEARCH_RESULTS = css("div[class='mat-tooltip-trigger product']");
  public static By OPEN_ITEM = css("div[class='item-name']");
  public static By REVIEWS_EXPAND = id("mat-expansion-panel-header-4");
  public static By COMMENT_SELECTION = id("div.comment:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
  // Public methods
  // -------------------------------------------------------------------------------------------------------------------
  public void waitForPageToLoad() {
    getElement(ITEMS_PER_PAGE).waitUntilVisible();
    logWeAreOnPage();
  }

  public Boolean validateSearchResults(DataTable data) {
    Boolean found;
    String name ;
    String description;
    String price;

    for (Map<String, String> map : dataToMap(data)) {
      found = false;
      name = map.get("Name");
      description = map.get("Description");
      price = map.get("Price");

      for (WebElementFacade element : getElements(BLOCK_NAME)) {
        if(element.getText().contains(name)){
          scrollIntoView(element);
          element.click();
          getElement(PRODUCT_INFO_BLOCK).waitUntilVisible();
          getElement(PRODUCT_INFO_PRICE).waitUntilVisible();

          Boolean containsDescriptionAndPrice = 
          getElement(PRODUCT_INFO_BLOCK).containsText(description) && 
          getElement(PRODUCT_INFO_PRICE).containsText(price);

          if(containsDescriptionAndPrice) found = true;
          getElement(PRODUCT_INFO_CLOSE).click();
        }
        if(found) break;
      }

      if(found){
        logValidationPassed(name);
      } else {
        logValidationFailed(name);
        return false;
      }
    }
    return true;
  }
}
