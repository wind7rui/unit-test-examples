package com.example.exception;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ExceptionMatches extends TypeSafeMatcher<DaoRuntimeException> {

    private String errorCode;

    private String errorMsg;

    public ExceptionMatches(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean matchesSafely(DaoRuntimeException arg0) {
        return arg0.getErrorCode().equals(errorCode) && arg0.getErrorMsg().equals(errorMsg);
    }

    public void describeTo(Description description) {
        description.appendText("expected errorCode ")
                .appendValue(errorCode).appendText("expectd errorMsg ").appendValue(errorMsg);
    }
}

