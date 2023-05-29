Feature: Search property with location and some other attributes
  In order to get correct result in SRP
  As the user
  I need to check the parsing URL after searching

  @complete
  Scenario:  Test baidu
    Given  I open the "baidu" page "https://www.baidu.com"

  @debug
  Scenario:  Test google
    Given  I open the "google" page "https://www.google.com"
    Then Search button value should be "Google 搜尋"
