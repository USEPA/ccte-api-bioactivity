package gov.epa.ccte.api.bioactivity.web.rest.error;


public class HigherNumberOfRequestsException extends RuntimeException {
    public HigherNumberOfRequestsException(Integer size, Integer maxSize){
        super(String.format("System supports only '%s' requested items at one time, '%s' are submitted.",maxSize, size));

    }
}