package com.example.zotreddit;

import org.parceler.Parcel;

@Parcel
public class Message {

    String post;
    String poster;
    int upvotes = 0;
    String key;

    public Message(String post, String poster, int upvotes, String key) {
        this.post = post;
        this.poster = poster;
        this.upvotes = upvotes;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getPost() {
        return post;
    }

    public String getPoster() {
        return poster;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public Message()
    {

    }
}
