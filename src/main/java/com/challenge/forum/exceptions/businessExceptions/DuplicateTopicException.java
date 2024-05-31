package com.challenge.forum.exceptions.businessExceptions;

public class DuplicateTopicException extends RuntimeException {

    public DuplicateTopicException(String message) {
        super(message);
    }
}
