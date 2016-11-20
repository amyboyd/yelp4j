package uk.co.amyboyd.yelp4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "rating", "excerpt"})
final public class Review {
    private String id;

    private int rating;

    private String excerpt;

    private int timeCreated;

    private String ratingImageUrl;

    @JsonProperty("rating_image_small_url")
    private String ratingImageUrlSmall;

    @JsonProperty("rating_image_large_url")
    private String ratingImageUrlLarge;

    @JsonProperty("user")
    private Reviewer reviewer;
}
