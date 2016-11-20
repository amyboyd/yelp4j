package uk.co.amyboyd.yelp4j.dto;

import lombok.Getter;

import java.util.List;

@Getter
final public class Location {
    /**
     * Address for this business. Only includes address fields.
     */
    private List<String> address;

    /**
     * Address for this business formatted for display.
     * <p>
     * Includes all address fields, cross streets and city, state_code, etc.
     */
    private List<String> displayAddress;

    /**
     * City for this business.
     */
    private String city;

    /**
     * ISO 3166-2 state code for this business.
     */
    private String stateCode;

    /**
     * Postal code for this business.
     */
    private String postalCode;

    /**
     * ISO 3166-1 country code for this business.
     */
    private String countryCode;

    /**
     * Cross streets for this business.
     */
    private String crossStreets;

    /**
     * List that provides neighborhood(s) information for business.
     */
    private List<String> neighborhoods;

    /**
     * Coordinates of this location.
     * <p>
     * This will be omitted if coordinates are not known for the location.
     */
    private Point coordinate;

    private double geoAccuracy;
}
