package com.hanrui.android.photogallery;

/**
 * @author
 * @version 1.0
 * @date 2017/6/18 0018
 */

public class GalleryItem {
    
    private String mCaption;
    private String mId;
    private String mUrl;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String toString(){
        return mCaption;
    }
}
