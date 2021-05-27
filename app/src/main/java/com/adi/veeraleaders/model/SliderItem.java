
package com.adi.veeraleaders.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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
