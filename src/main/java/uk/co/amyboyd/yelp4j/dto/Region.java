package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

/**
 * Suggested bounds in a map to display results in.
 */
@Getter
final public class Region {
    /**
     * Span of suggested map bounds.
     */
    private Span span;

    /**
     * Center position of map bounds.
     */
    private Point center;
}
