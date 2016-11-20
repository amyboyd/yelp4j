package uk.co.amyboyd.yelp4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
public class SearchResult {
    /**
     * Yelp ID for this business.
     */
    private String id;

    /**
     * Name of this business.
     */
    private String name;

    /**
     * URL for business page on Yelp.
     */
    private String url;

    /**
     * URL for mobile business page on Yelp.
     */
    private String mobileUrl;

    /**
     * Number of reviews for this business.
     */
    private int reviewCount;

    /**
     * Snippet text associated with this business.
     */
    @JsonProperty("snippet_text")
    private String reviewSnippetText;

    /**
     * URL to star rating image for this business (size = 84x17).
     */
    @JsonProperty("rating_img_url")
    private String ratingImageUrl;

    /**
     * URL to small version of rating image for this business (size = 50x10).
     */
    @JsonProperty("rating_img_url_small")
    private String ratingImageUrlSmall;

    /**
     * URL to large version of rating image for this business (size = 166x30).
     */
    @JsonProperty("rating_img_url_large")
    private String ratingImageUrlLarge;

    /**
     * Whether business has been claimed by a business owner.
     */
    @JsonProperty("is_claimed")
    private boolean isClaimed;

    /**
     * Whether business has been (permanently) closed.
     */
    @JsonProperty("is_closed")
    private boolean isClosed;

    /**
     * URL of photo for this business.
     */
    private String imageUrl;

    /**
     * Phone number for this business with international dialing code (e.g. +442079460000)
     */
    @JsonProperty("phone")
    private String phoneNumber;

    /**
     * Phone number for this business formatted for display.
     */
    @JsonProperty("display_phone")
    private String displayPhoneNumber;

    /**
     * Provides a list of category name, alias pairs that this business is associated with. For example:
     * [["Local Flavor", "localflavor"], ["Active Life", "active"], ["Mass Media", "massmedia"]]
     * <p>
     * The alias is provided so you can search with the category_filter.
     */
    private List<String[]> categories;

    /**
     * Rating for this business (value ranges from 1, 1.5, ... 4.5, 5).
     */
    private double rating;

    /**
     * URL of snippet image associated with this business.
     */
    private String snippetImageUrl;

    /**
     * Location data for this business.
     */
    private Location location;

    /**
     * Deal info for this business (optional: this field is present only if there’s a Deal).
     */
    private List<Deal> deals;

    /**
     * Gift certificate info for this business (optional: this field is present only if there are gift certificates
     * available).
     */
    private List<GiftCertificate> giftCertificates;

    /**
     * Provider of the menu for this business.
     */
    private String menuProvider;

    /**
     * Last time this menu was updated on Yelp (Unix timestamp).
     */
    private long menuDateUpdated;

    /**
     * URL to the SeatMe reservation page for this business. This will not be present if the business does not take
     * reservations through SeatMe or if the query param 'actionlinks' is not set to True in the request.
     */
    private String reservationUrl;

    /**
     * URL to the Eat24 page for this business. This key will not be present if the business does not offer delivery
     * through Eat24 or if the query param 'actionlinks' is not set to True in the request.
     */
    private String eat24Url;
}
