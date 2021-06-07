
package com.adi.veeraleaders.model.coupon;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CouponResponse {

    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Result")
    @Expose
    private Boolean result;
    @SerializedName("ResponseMsg")
    @Expose
    private String responseMsg;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
