Feature: Search for an iphone on Amazon and add it to the basket
  As a user of amazon.com
  I should be able to search for an iPhone, order the results by price and add the item to my basket

  Scenario: User searches for an iPhone and adds the cheapest one to their basket
    Given I open amazon.com
    And I search for "iPhone"
    And I receive results with prices
    When I sort by price ascending
    Then I can add the cheapest item to my basket