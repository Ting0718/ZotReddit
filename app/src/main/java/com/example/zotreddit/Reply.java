package com.example.zotreddit;

import org.parceler.Parcel;

@Parcel
public class Reply {

    String post;
    String poster;
    int upvotes;
    String key;

    public Reply(String poster, String post, int upvotes) {
        this.poster = poster;
        this.post = post;
        this.upvotes = upvotes;
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


}


