package techcourse.myblog.application.exception;

import java.util.NoSuchElementException;

public class NoUserException extends NoSuchElementException {
    public NoUserException(String message) {
        super(message);
    }
}
