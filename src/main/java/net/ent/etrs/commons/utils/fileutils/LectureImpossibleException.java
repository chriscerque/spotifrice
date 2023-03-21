package net.ent.etrs.commons.utils.fileutils;

public class LectureImpossibleException extends Exception {
    public LectureImpossibleException() {
    }

    public LectureImpossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LectureImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public LectureImpossibleException(String message) {
        super(message);
    }

    public LectureImpossibleException(Throwable cause) {
        super(cause);
    }
}
