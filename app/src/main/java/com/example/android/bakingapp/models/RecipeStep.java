package com.example.android.bakingapp.models;

import java.io.Serializable;

public class RecipeStep implements Serializable {
    private int mStepId;
    private String mShortDescription;
    private String mDescription;
    private String mVideoURL;
    private String mThumbnailURL;

    public RecipeStep(int stepId, String shortDescription, String description, String videoURL, String thumbnailURL) {
        mStepId = stepId;
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoURL = videoURL;
        mThumbnailURL = thumbnailURL;
    }

    public int getmStepId() {
        return mStepId;
    }

    public void setmStepId(int mStepId) {
        this.mStepId = mStepId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoURL() {
        return mVideoURL;
    }

    public void setmVideoURL(String mVideoURL) {
        this.mVideoURL = mVideoURL;
    }

    public String getmThumbnailURL() {
        return mThumbnailURL;
    }

    public void setmThumbnailURL(String mThumbnailURL) {
        this.mThumbnailURL = mThumbnailURL;
    }
}
