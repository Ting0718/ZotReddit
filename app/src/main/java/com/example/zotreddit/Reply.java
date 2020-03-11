package com.example.zotreddit;

import org.parceler.Parcel;

@Parcel
public class Reply {

    private String post;
    private String poster;
    private int upvotes;
    private String key;

    public Reply(String poster, String post, int upvotes, String key) {
        this.poster = poster;
        this.post = post;
        this.upvotes = upvotes;
        this.key = key;
    }

    public Reply()
    {

    }

    public String getPoster() {
        return poster;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getPost() {
        return post;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


