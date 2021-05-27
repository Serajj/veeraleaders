
package com.adi.veeraleaders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("adhar_no")
    @Expose
    private Object adharNo;
    @SerializedName("bank_acc")
    @Expose
    private Object bankAcc;
    @SerializedName("bank_ifsc")
    @Expose
    private Object bankIfsc;
    @SerializedName("win_amt")
    @Expose
    private Object winAmt;
    @SerializedName("bet_amt")
    @Expose
    private Object betAmt;
    @SerializedName("level")
    @Expose
    private Object level;
    @SerializedName("bank_name")
    @Expose
    private Object bankName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("device_id")
    @Expose
    private Object deviceId;
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("rid")
    @Expose
    private String rid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(Object adharNo) {
        this.adharNo = adharNo;
    }

    public Object getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(Object bankAcc) {
        this.bankAcc = bankAcc;
    }

    public Object getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(Object bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public Object getWinAmt() {
        return winAmt;
    }

    public void setWinAmt(Object winAmt) {
        this.winAmt = winAmt;
    }

    public Object getBetAmt() {
        return betAmt;
    }

    public void setBetAmt(Object betAmt) {
        this.betAmt = betAmt;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
