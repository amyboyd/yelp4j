package uk.co.amyboyd.yelp4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import lombok.extern.slf4j.Slf4j;
import uk.co.amyboyd.yelp4j.dto.Business;
import uk.co.amyboyd.yelp4j.dto.SearchResult;
import uk.co.amyboyd.yelp4j.dto.SearchResults;
import uk.co.amyboyd.yelp4j.exception.YelpException;
import uk.co.amyboyd.yelp4j.exception.YelpHttpException;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@SuppressWarnings("WeakerAccess")
public class YelpApiService {
    private static final String BASE_API_HOST = "https://api.yelp.com";

    private static final String SEARCH_PATH = "/v2/search";

    private static final String BUSINESS_PATH = "/v2/business";

    private final OAuth10aService service;

    private final OAuth1AccessToken accessToken;

    private final ObjectMapper objectMapper = new ObjectMapper()
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public YelpApiService(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder()
            .apiKey(consumerKey)
            .apiSecret(consumerSecret)
            .build(new DefaultApi10a() {
                @Override
                public String getAccessTokenEndpoint() {
                    throw methodNotImplemented("getAccessTokenEndpoint");
                }

                @Override
                public String getRequestTokenEndpoint() {
                    throw methodNotImplemented("getRequestTokenEndpoint");
                }

                @Override
                public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
                    throw methodNotImplemented("getAuthorizationUrl");
                }

                private RuntimeException methodNotImplemented(String methodName) {
                    return new RuntimeException(methodName + " should not be called within yelp4j");
                }
            });

        this.accessToken = new OAuth1AccessToken(token, tokenSecret);
    }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
     * for more info.
     * <p>
     * Returns the first business in Yelp's response, which may not be exactly what you need. Use
     * {@link #searchForBusinessesByLocation(String, String, Integer)} instead if you want to inspect multiple results
     * to choose the best one.
     *
     * @param location A human-readable string, for example "N1, London".
     */
    public Optional<SearchResult> searchForBusinessByLocation(String term, String location) {
        SearchResults results = searchForBusinessesByLocation(term, location, 1);

        if (results.getTotal() > 0) {
            return Optional.of(results.getBusinesses().get(0));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
     * for more info.
     *
     * @param location A human-readable string, for example "N1, London".
     */
    public SearchResults searchForBusinessesByLocation(String term, String location, Integer limit) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        if (location != null) {
            request.addQuerystringParameter("location", location);
        }
        if (limit != null) {
            request.addQuerystringParameter("limit", String.valueOf(limit));
        }

        String response = sendRequestAndGetResponse(request);

        return deserializeJson(response, SearchResults.class);
    }

    /**
     * Creates and sends a request to the Business API by business ID.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
     * for more info.
     *
     * @param businessId Yelp's ID of the requested business.
     */
    public Optional<Business> getBusinessById(String businessId) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessId);

        String response;
        try {
            response = sendRequestAndGetResponse(request);
        } catch (YelpHttpException e) {
            if (e.getCode() == 400) {
                return Optional.empty();
            } else {
                throw e;
            }
        }

        Business pojo = deserializeJson(response, Business.class);

        return Optional.of(pojo);
    }

    private OAuthRequest createOAuthRequest(String path) {
        return new OAuthRequest(Verb.GET, BASE_API_HOST + path);
    }

    /**
     * @return JSON body of API response.
     * @throws YelpHttpException if the Yelp API returns a non-200 status code.
     * @throws YelpException     if there is an error making the request.
     */
    private String sendRequestAndGetResponse(OAuthRequest request) {
        String url = request.getCompleteUrl();

        log.info("Querying Yelp API: {}", url);

        service.signRequest(accessToken, request);

        Response response;
        try {
            response = service.execute(request);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new YelpException(e);
        }

        String body;
        try {
            body = response.getBody();
        } catch (IOException e) {
            throw new YelpException(e);
        }

        if (response.getCode() != 200) {
            log.error("Yelp API returned response code {}: {}", response.getCode(), body);
            throw new YelpHttpException(response.getCode(), body);
        }

        return body;
    }

    private <T> T deserializeJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new YelpException(e);
        }
        return pojo;
    }
}
