package com.projectname.api.client.data.model.crocodile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrocodileErrorResponse implements Serializable {

    @SerializedName("detail")
    @Expose
    private String detail;


    public CrocodileErrorResponse() {
    }


    public CrocodileErrorResponse(String detail) {
        super();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
