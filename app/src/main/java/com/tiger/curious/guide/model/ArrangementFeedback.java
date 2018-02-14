package com.tiger.curious.guide.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkang016 on 9/21/17.
 */

public class ArrangementFeedback {
    private String error;
    private String message;


    private List<Company> data = new ArrayList<>();

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Company> getData() {
        return data;
    }

    public void setData(List<Company> data) {
        this.data = data;
    }
}
