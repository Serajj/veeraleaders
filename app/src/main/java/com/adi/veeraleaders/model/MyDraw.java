
package com.adi.veeraleaders.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyDraw {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("amt")
    @Expose
    private String amt;
    @SerializedName("win_op")
    @Expose
    private Object winOp;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("my_ans")
    @Expose
    private String myAns;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public Object getWinOp() {
        return winOp;
    }

    public void setWinOp(Object winOp) {
        this.winOp = winOp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMyAns() {
        return myAns;
    }

    public void setMyAns(String myAns) {
        this.myAns = myAns;
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
