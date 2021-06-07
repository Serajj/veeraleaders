
package com.adi.veeraleaders.model.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("valid_date")
    @Expose
    private String validDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("ref_uid")
    @Expose
    private Object refUid;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getRefUid() {
        return refUid;
    }

    public void setRefUid(Object refUid) {
        this.refUid = refUid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
