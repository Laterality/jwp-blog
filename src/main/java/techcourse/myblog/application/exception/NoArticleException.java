package techcourse.myblog.application.exception;

import java.util.NoSuchElementException;

public class NoArticleException extends NoSuchElementException {
    public NoArticleException(String message) {
        super(message);
    }
}
