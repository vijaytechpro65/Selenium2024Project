package com.qa.opencart.utils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import java.io.FileNotFoundException;

public class CommonErrors {

    // Common error messages
    public static final String ELEMENT_NOT_FOUND = "The requested element was not found on the page.";
    public static final String PAGE_NOT_LOADED = "The page did not load within the specified timeout.";
    public static final String INVALID_CREDENTIALS = "Invalid username or password.";
    public static final String ASSERTION_FAILED = "Assertion failed. Expected value does not match actual value.";
    public static final String FILE_NOT_FOUND = "The specified file was not found.";
    public static final String TIMEOUT_EXCEPTION = "The operation timed out.";
    public static final String STALE_ELEMENT_EXCEPTION = "The element is no longer attached to the DOM.";
    public static final String NO_SUCH_ELEMENT_EXCEPTION = "The element was not found in the DOM.";
    public static final String ELEMENT_NOT_CLICKABLE = "The element was not clickable.";
    public static final String ELEMENT_NOT_VISIBLE = "The element was not visible.";
    public static final String UNEXPECTED_ALERT_PRESENT = "An unexpected alert was present.";

    // Common exception handling methods
    public static void handleNoSuchElementException(NoSuchElementException e) {
        System.err.println(NO_SUCH_ELEMENT_EXCEPTION);
        e.printStackTrace();
    }

    public static void handleTimeoutException(TimeoutException e) {
        System.err.println(TIMEOUT_EXCEPTION);
        e.printStackTrace();
    }

    public static void handleStaleElementException(StaleElementReferenceException e) {
        System.err.println(STALE_ELEMENT_EXCEPTION);
        e.printStackTrace();
    }

    public static void handleFileNotFoundException(FileNotFoundException e) {
        System.err.println(FILE_NOT_FOUND);
        e.printStackTrace();
    }


    // You can add more methods as needed for other exceptions
}
