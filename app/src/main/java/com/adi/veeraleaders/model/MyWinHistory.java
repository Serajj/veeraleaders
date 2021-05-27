
package com.adi.veeraleaders.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyWinHistory {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("amt")
    @Expose
    private String amt;
    @SerializedName("upi_id")
    @Expose
    private String upiId;
    @SerializedName("payment_time")
    @Expose
    private String paymentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

}
