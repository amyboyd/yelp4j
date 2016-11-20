package uk.co.amyboyd.yelp4j.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
final public class Reviewer {
    private String id;

    private String name;

    private String imageUrl;
}
