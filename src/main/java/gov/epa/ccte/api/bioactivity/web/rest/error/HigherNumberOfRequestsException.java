package gov.epa.ccte.api.bioactivity.web.rest.error;

public class HigherNumberOfRequestsException extends RuntimeException {

    public HigherNumberOfRequestsException(Integer size, Integer maxSize) {
        super(String.format("Bad Request - maximum %s AED IDs per request (received %s)", maxSize, size));    }
}
