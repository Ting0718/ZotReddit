package com.example.zotreddit;

import org.parceler.Parcel;

@Parcel
public class Reply {

    private String post;
    private String poster;
    private int upvotes;
    private String key;
    private String parent_key;

    public Reply(String poster, String post, int upvotes, String key, String parent_key) {
        this.poster = poster;
        this.post = post;
        this.upvotes = upvotes;
        this.key = key;
        this.parent_key = parent_key;
    }

    public Reply()
    {

    }

    public String getParent_key() {
        return parent_key;
    }

    public String getPoster() {
        return poster;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setParent_key(String parent_key) {
        this.parent_key = parent_key;
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


