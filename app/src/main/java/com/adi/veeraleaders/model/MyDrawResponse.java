
package com.adi.veeraleaders.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyDrawResponse {

    @SerializedName("MyDraws")
    @Expose
    private List<MyDraw> myDraws = null;
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Result")
    @Expose
    private Boolean result;
    @SerializedName("ResponseMsg")
    @Expose
    private String responseMsg;

    public List<MyDraw> getMyDraws() {
        return myDraws;
    }

    public void setMyDraws(List<MyDraw> myDraws) {
        this.myDraws = myDraws;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

}
