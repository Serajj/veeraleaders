
package com.adi.veeraleaders.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LuckyDraw {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("op_i")
    @Expose
    private String opI;
    @SerializedName("op_ii")
    @Expose
    private String opIi;
    @SerializedName("op_iii")
    @Expose
    private String opIii;
    @SerializedName("op_iv")
    @Expose
    private String opIv;
    @SerializedName("op_v")
    @Expose
    private String opV;
    @SerializedName("op_vi")
    @Expose
    private String opVi;
    @SerializedName("amt")
    @Expose
    private String amt;
    @SerializedName("win_amt")
    @Expose
    private String winAmt;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("seraj")
    @Expose
    private String seraj;
    @SerializedName("alam")
    @Expose
    private String alam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpI() {
        return opI;
    }

    public void setOpI(String opI) {
        this.opI = opI;
    }

    public String getOpIi() {
        return opIi;
    }

    public void setOpIi(String opIi) {
        this.opIi = opIi;
    }

    public String getOpIii() {
        return opIii;
    }

    public void setOpIii(String opIii) {
        this.opIii = opIii;
    }

    public String getOpIv() {
        return opIv;
    }

    public void setOpIv(String opIv) {
        this.opIv = opIv;
    }

    public String getOpV() {
        return opV;
    }

    public void setOpV(String opV) {
        this.opV = opV;
    }

    public String getOpVi() {
        return opVi;
    }

    public void setOpVi(String opVi) {
        this.opVi = opVi;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getWinAmt() {
        return winAmt;
    }

    public void setWinAmt(String winAmt) {
        this.winAmt = winAmt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSeraj() {
        return seraj;
    }

    public void setSeraj(String seraj) {
        this.seraj = seraj;
    }

    public String getAlam() {
        return alam;
    }

    public void setAlam(String alam) {
        this.alam = alam;
    }

}
