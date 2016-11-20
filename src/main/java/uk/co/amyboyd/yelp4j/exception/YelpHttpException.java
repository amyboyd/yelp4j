package uk.co.amyboyd.yelp4j.exception;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
final public class YelpHttpException extends YelpException {
    private final int code;

    private final String body;

    public YelpHttpException(int code, String body) {
        this.code = code;
        this.body = body;
    }
}
