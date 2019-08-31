package dev.estimate.springtut.domain;

public class SessionNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Session with this ID is not present in the repository";
    }
}
