package com.oneupapplications.layoutplayground.model;

import java.util.ArrayList;

public class article {

    private long id;
    private String PostTitle;
    private String PostBody;
    private String ImageUrl;
    private String PredictionYear;
    private String Tags;
    private String Likeliness;

    public static String tPostTitle = "PostTitle";
    public static String tPostBody = "PostBody";
    public static String tImageUrl = "ImageUrl";
    public static String tPredictionYear = "PredictionYear";
    public static String tTags = "Tags";
    public static String tLikeliness = "Likeliness";

    //    Version
    //    PredictionStatus {True, False, TBD}
    //    Timeline {Near, Soon, Later, Distant, Far Distant}
    //    PredictionDate
    //    DateWritten
    //    DateActual {TBD / Date string}
    //    Sources
    //    ContributingAuthorName
    //    ContributingOriginalContent
    //    ContributingAuthorLink
    //    ResolutionEditorial
    //    FollowupArticles



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //-------------------------

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String id) {
        this.PostTitle = id;
    }

    //-------------------------

    public String getPostBody() {
        return PostBody;
    }

    public void setPostBody(String id) {
        this.PostBody = id;
    }

    //-------------------------

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String id) {
        this.ImageUrl = id;
    }

    //-------------------------

    public String getPredictionYear() {
        return PredictionYear;
    }

    public void setPredictionYear(String id) {
        this.PredictionYear = id;
    }

    //-------------------------

    public String getTags() {
        return Tags;
    }

    public void setTags(String id) {
        this.Tags = id;
    }

    //-------------------------

    public String getLikeliness() {
        return Likeliness;
    }

    public void setLikeliness(String id) {
        this.Likeliness = id;
    }

    //-------------------------
}
