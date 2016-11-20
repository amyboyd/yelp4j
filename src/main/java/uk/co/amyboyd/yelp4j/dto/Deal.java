package uk.co.amyboyd.yelp4j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
final public class Deal {
    /**
     * Deal identifier.
     */
    private String id;

    /**
     * Deal title.
     */
    private String title;

    /**
     * Deal URL.
     */
    private String url;

    /**
     * Deal image URL.
     */
    private String imageUrl;

    /**
     * ISO-4217 currency code.
     */
    private String currencyCode;

    /**
     * Deal start time (Unix timestamp).
     */
    @JsonProperty("time_start")
    private long startTime;

    /**
     * Deal end time (optional: this field is present only if the Deal ends).
     */
    @JsonProperty("time_end")
    private long endTime;

    /**
     * Whether the Deal is popular (optional: this field is present only if true).
     */
    @JsonProperty("is_popular")
    private boolean isPopular;

    /**
     * Additional details for the Deal, separated by newlines.
     */
    private String whatYouGet;

    /**
     * Important restrictions for the Deal, separated by newlines.
     */
    private String importantRestrictions;

    /**
     * Deal additional restrictions.
     */
    private String additionalRestrictions;

    /**
     * Deal options.
     */
    private List<DealOption> options;
}
