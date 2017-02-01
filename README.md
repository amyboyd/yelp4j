Yelp4J
======

Installation
------------

Add to your pom.xml:

```xml
<dependency>
    <groupId>uk.co.amyboyd.yelp4j</groupId>
    <artifactId>yelp4j</artifactId>
    <version>LATEST</version>
</dependency>
```

Usage
-----

```java
YelpApiService service = new YelpApiService("yourConsumerKey", "yourConsumerSecret", "yourToken", "yourTokenSecret");

// Search for businesses by name and location, with the limit set to 3.
SearchResults results = service.searchForBusinessesByLocation("Chilango", "London", 3);

// Search for one business by name and location.
Optional<SearchResult> resultOption = service.searchForBusinessByLocation("The Windmill", "EC2A 4DE, London");

// Get a business by it's Yelp ID.
Optional<Business> resultOption = service.getBusinessById("chilango-london-2");
````

(You can get your key/token/secrets from [https://www.yelp.com/developers/v2/manage_api_keys](https://www.yelp.com/developers/v2/manage_api_keys)).
