
package com.hcl.library.exception;

public class LibraryException extends Exception {

    public LibraryException(String message) {
        super(message);
    }

    public static class ItemNotFoundException extends LibraryException {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class ItemNotAvailableException extends LibraryException {
        public ItemNotAvailableException(String message) {
            super(message);
        }
    }

    public static class BorrowLimitExceededException extends LibraryException {
        public BorrowLimitExceededException(String message) {
            super(message);
        }
    }
}
