package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

import java.util.List;

@Getter
final public class GiftCertificate {
    /**
     * Gift certificate identifier.
     */
    private String id;

    /**
     * Gift certificate landing page URL.
     */
    private String url;

    /**
     * Gift certificate image URL.
     */
    private String imageUrl;

    /**
     * ISO-4217 currency code.
     */
    private String currencyCode;

    /**
     * Whether unused balances are returned as cash or store credit.
     */
    private String unusedBalances;

    /**
     * Gift certificate options.
     */
    private List<GiftCertificateOption> options;
}
