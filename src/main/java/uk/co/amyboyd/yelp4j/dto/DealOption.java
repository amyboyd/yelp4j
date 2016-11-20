package uk.co.amyboyd.yelp4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
final public class DealOption {
    /**
     * Deal option title.
     */
    private String title;

    /**
     * Deal option URL for purchase.
     */
    private String purchase_url;

    /**
     * Deal option price (in cents).
     */
    private double price;

    /**
     * Deal option price (formatted, e.g. "$6").
     */
    private String formattedPrice;

    /**
     * Deal option original price (in cents).
     */
    private double originalPrice;

    /**
     * Deal option original price (formatted, e.g. "$12").
     */
    private String formattedOriginalPrice;

    /**
     * Whether the deal option is limited or unlimited.
     */
    @JsonProperty("is_quantity_limited")
    private boolean isQuantityLimited;

    /**
     * The remaining deal options available for purchase.
     * <p>
     * (This field is only present if the deal is limited.)
     */
    private Integer remainingCountNumber;
}
