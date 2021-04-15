Feature: Juice Shop Actions
  Background:
    Given I am on the "Home" page
    And   I click "Dismiss button"
    And   I click "Me want it button"


  Scenario: Set items per page
    When I click "Items per page"
    And  I click "Option 24"
    Then I see "Item list" with the following data:
      | Amount |
      | 24     |
  @run
  Scenario: Search for 500ml
    When I search for "500ml"
    Then I see "search results" with the following data:
      | Name                     | Description                        | Price |
      | Eggfruit Juice (500ml)   | Now with even more exotic flavour. | 8.99  |
      | Lemon Juice (500ml)      | Sour but full of vitamins.         | 2.99  |
      | Strawberry Juice (500ml) | Sweet & tasty!                     | 3.99  |

  Scenario: Find comment
    When I search for "King of the Hill"
    And  I "open item" with the following data:
      | Name             |
      | King of the Hill |
    And  I click "Reviews expand"
    Then "Comment section" should contain "K33p5 y0ur ju1cy 5plu773r 70 y0ur53lf!"

  Scenario: Add comment
    Then I "log in" with the following data:
      | Email | Password |
      | demo  | demo     |
    When I click "Search"
    And  I search for "2020"
    And  I "open item" with the following data:
      | Name                                 |
      | Juice Shop "Permafrost" 2020 Edition |
    Then "Product Info Block" should be "visible"
    When I set "Review text field" to "Travel to Norway for more info"
    And  I click "Submit Button"
    And  I click "Reviews Expand"
    Then "Comment section" should contain "Travel to Norway for more info"

  Scenario: Create new Juice Shop user
    When I click "Account button"
    And  I click "Login button"
    And  I click "Not yet a customer"
    And  I set "Email field" to "RANDOM_EMAIL"
    And  I set "Password field" to "easyPassword123"
    And  I set "Repeat Password field" to "easyPassword123"
    And  I click "Security question"
    And  I click "Name of favorite pet option"
    And  I set "Security answer field" to "Mushu"
    And  I click "Registration button"
    Then I should be on "Login" page
    And  "Registration completed successfully." should be "visible"

  Scenario Outline: Change language
    When I "log in" with the following data:
      | Email | Password |
      | demo  | demo     |
    And  I "Select language" with the following data:
      | Language   |
      | <Language> |
    Then "Simple snack bar" should contain "Language has been changed to <Language>"
    When I click "Account button"
    Then I see "Account menu" with the following data:
      | Orders And Payment option | Privacy And Security option | Logout button |
      | <Example 1>               | <Example 2>                 | <Example 3>   |

  Examples:
    | Language    | Example 1           | Example 2                         | Example 3     |
    | Suomalainen | Tilaukset ja Maksut | Yksityisyys & Turvallisuus        | Kirjaudu ulos |
    | Pусский     | Заказы и Оплата     | Конфиденциальность и безопасность | Выход         |
    | 日本の       | 注文と支払い          | プライバシーとセキュリテ              |  ログアウト     |

  Scenario: Increase wallet balance
    When I click "Account button"
    And  I click "Login button"
    Then  I click "Not yet a customer"
    When I "create an account" with the following data:
      | Email field  | Password field | Repeat Password field | Security Question | Security Answer field                                |
      | RANDOM_EMAIL | password       | password              | favorite book     | University Physics with Modern Physics, 13th Edition |
    And  I "log in" with the following data:
      | Email        | Password |
      | RANDOM_EMAIL | password |
    And  I click "Account button"
    And  I click "Orders and Payment option"
    And  I click "Digital Wallet option"
    Then I am on the "Wallet" page
    And  "Wallet balance" should contain "0.00"
    When I set "Amount Field" to "999"
    And  I click "Submit button"
    And  I click "Add new card option"
    And  I set "Card name field" to "American Express 9000"
    And  I set "Card number field" to "1234567890123456"
    And  I set "Expiry Month" to "10"
    And  I set "Expiry Year" to "2099"
    And  I click "Submit button"
    And  I "select credit card" with the following data:
      | Card Number |
      | 3456        |
    And  I click "Continue button"
    Then "Wallet balance" should contain "999.00"

  Scenario: Change password
    When I click "Account button"
    And  I click "Login button"
    Then  I click "Not yet a customer"
    When I "create an account" with the following data:
      | Email field  | Password field | Repeat Password field | Security Question | Security Answer field                                |
      | RANDOM_EMAIL | password       | password              | favorite book     | University Physics with Modern Physics, 13th Edition |
    And  I "log in" with the following data:
      | Email        | Password |
      | RANDOM_EMAIL | password |
    And  I click "Account button"
    And  I click "Privacy And Security option"
    And  I click "Change password button"
    And  I set "Current Password field" to "password"
    And  I set "New Password field" to "SeriousHeadAche"
    And  I set "Repeat New Password field" to "SeriousHeadAche"
    And  I click "Change button"
    Then "Your password was successfully changed." should be "visible"
    When I click "Account button"
    And  I click "Logout button"
    And  I "log in" with the following data:
      | Email        | Password        |
      | RANDOM_EMAIL | SeriousHeadAche |
    Then I am on the "Home" page

  Scenario: Add new address
    When I "log in" with the following data:
      | Email | Password |
      | demo  | demo     |
    Then I click "Search"
    And  I search for "Apple Juice"
    And  I click "Add to basket button"
    And  I click "Shopping cart button"
    And  I click "Checkout button"
    And  I click "Add new address button"
    And  I set "Country field" to "Sweden"
    And  I set "Name field" to "Karlson"
    And  I set "Mobile Number field" to "4677179333"
    And  I set "ZIP code field" to "54639385"
    And  I set "Address field" to "Random Address 25-395"
    And  I set "City field" to "Malgovik"
    And  I set "State field" to "Lappland"
    And  I click "Submit button"
    Then I see "address" with the following data:
      | Name    | Address               | Country |
      | Karlson | Random Address 25-395 | Sweden  |

  Scenario: Order an item
    When I "log in" with the following data:
      | Email | Password |
      | demo  | demo     |
    And I search for "Apple Juice"
    And  I click "Add to basket button"
    And  I click "Shopping cart button"
    Then I see "basket content" with the following data:
      | Item                 |
      | Apple Juice (1000ml) |
    When I click "Checkout button"
    Then I am on the "Address select" page
    When I "select Address" with the following data:
      | Address        |
      | Dummystreet 42 |
    And  I click "Continue button"
    Then I am on the "Delivery Method" page
    When I "Select delivery speed" with the following data:
      | Delivery speed |
      | Fast Delivery  |
    And  I click "Continue button"
    Then I am on the "Payment Shop" page
    When I "select credit card" with the following data:
      | Card Number |
      | 5678        |
    And  I click "Continue button"
    Then I am on the "Order Summary" page
    When I click "Place your order and pay button"
    Then I am on the "Order completion" page
