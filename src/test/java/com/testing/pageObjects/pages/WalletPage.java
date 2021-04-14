package com.testing.pageObjects.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/wallet")
public class WalletPage extends HomePage {
  // Locators ----------------------------------------------------------------------------------------------------------
  public static By DISMISS_BUTTON;
  public static By WALLET_TITLE = text("Digital Wallet");
  public static By WALLET_BALANCE = css("span[class='confirmation']");
  public static By AMOUNT_FIELD = id("mat-input-33");
  public static By CONTINUE_BUTTON = id("submitButton");

  // Public methods ----------------------------------------------------------------------------------------------------
  public void waitForPageToLoad() {
    getElement(WALLET_TITLE).waitUntilPresent();
    logWeAreOnPage();
  }
}
