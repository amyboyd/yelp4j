package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

@Getter
final public class GiftCertificateOption {
    /**
     * Gift certificate option price (in cents).
     */
    private double price;

    /**
     * Gift certificate option price (formatted, e.g. "$50").
     */
    private String formattedPrice;
}
