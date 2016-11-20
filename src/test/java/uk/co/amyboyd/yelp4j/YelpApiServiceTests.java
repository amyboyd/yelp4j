package uk.co.amyboyd.yelp4j;

import org.junit.Test;
import uk.co.amyboyd.yelp4j.dto.Business;
import uk.co.amyboyd.yelp4j.dto.SearchResult;
import uk.co.amyboyd.yelp4j.dto.SearchResults;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.*;

public class YelpApiServiceTests {
    private YelpApiService service;

    public YelpApiServiceTests() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("src/test/java/uk/co/amyboyd/yelp4j/test.properties");
        properties.load(input);
        input.close();

        service = new YelpApiService(
            properties.getProperty("consumerKey"),
            properties.getProperty("consumerSecret"),
            properties.getProperty("token"),
            properties.getProperty("tokenSecret")
        );
    }

    @Test
    public void searchForBusinessByLocationShouldReturnABusinessSearchResult() {
        Optional<SearchResult> resultOption = service.searchForBusinessByLocation("The Windmill", "London");
        assertTrue(resultOption.isPresent());

        SearchResult result = resultOption.get();
        assertEquals("The Windmill", result.getName());
        assertEquals("London", result.getLocation().getCity());
    }

    @Test
    public void searchForBusinessByLocationShouldReturnAnEmptyOptionalIfNoSearchResult() {
        Optional<SearchResult> resultOption = service.searchForBusinessByLocation("klmmnĸoinhjnkaerhstyj", "EC2A 4DE, London");
        assertFalse(resultOption.isPresent());
    }

    @Test
    public void searchForBusinessesByLocationShouldReturnSomeBusinessSearchResults() {
        SearchResults results = service.searchForBusinessesByLocation("Chilango", "London", 3);
        assertEquals(3, results.getBusinesses().size());
        assertTrue(results.getTotal() >= results.getBusinesses().size());

        for (SearchResult result : results.getBusinesses()) {
            assertEquals("Chilango", result.getName());
        }
    }

    @Test
    public void getBusinessByIdShouldReturnABusiness() {
        Optional<Business> resultOption = service.getBusinessById("chilango-london-2");
        assertTrue(resultOption.isPresent());

        Business result = resultOption.get();
        assertEquals("chilango-london-2", result.getId());
        assertEquals("Chilango", result.getName());
        assertTrue(result.getReviews().size() > 0);
        assertTrue(result.getReviewCount() >= result.getReviews().size());
        assertFalse(result.isClosed());
        assertTrue(result.isClaimed());
    }

    @Test
    public void getBusinessByIdShouldReturnAnEmptyOptionIfAnInvalidIdIsGiven() {
        Optional<Business> resultOption = service.getBusinessById("bunny-cafe");
        assertFalse(resultOption.isPresent());
    }
}
