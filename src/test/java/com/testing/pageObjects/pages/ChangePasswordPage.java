package com.testing.pageObjects.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/privacy-security/change-password")
public class ChangePasswordPage extends TopMenuSection {
  public static By CHANGE_PASSWORD_TITLE = text("Change Password");
  public static By CURRENT_PASSWORD_FIELD = id("currentPassword");
  public static By NEW_PASSWORD_FIELD = id("newPassword");
  public static By REPEAT_NEW_PASSWORD_FIELD = id("newPasswordRepeat");
  public static By CHANGE_BUTTON = id("changeButton");
  public static By YOUR_PASSWORD_WAS_SUCCESSFULLY_CHANGED = text("Your password was successfully changed.");
  // Public methods ----------------------------------------------------------------------------------------------------
  public void waitForPageToLoad() {
    getElement(CHANGE_PASSWORD_TITLE).waitUntilPresent();
    logWeAreOnPage();
  }
}
