package uk.co.amyboyd.yelp4j.exception;

@SuppressWarnings("serial")
public class YelpException extends RuntimeException {
    YelpException() {
    }

    public YelpException(Throwable e) {
        super(e);
    }
}
