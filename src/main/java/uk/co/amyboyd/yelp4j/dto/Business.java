package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

import java.util.List;

@Getter
final public class Business extends SearchResult {
    private List<Review> reviews;
}
