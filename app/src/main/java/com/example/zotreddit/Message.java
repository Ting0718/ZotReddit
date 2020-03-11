package com.example.zotreddit;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Message {

    private String post;
    private String poster;
    private int upvotes = 0;
    private String key;
    private ArrayList<Reply> replies;

    public Message()
    {

    }

    public Message(String post, String poster, int upvotes, ArrayList<Reply> replies, String key) {
        this.post = post;
        this.poster = poster;
        this.upvotes = upvotes;
        this.replies = replies;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
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

    public void addReplies(Reply reply) {
        this.replies.add(reply);
    }
}
