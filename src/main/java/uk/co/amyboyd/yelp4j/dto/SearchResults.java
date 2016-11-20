package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

import java.util.List;

@Getter
final public class SearchResults {
    private int total;

    private List<SearchResult> businesses;

    private Region region;
}
