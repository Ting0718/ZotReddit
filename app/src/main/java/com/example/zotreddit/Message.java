package com.example.zotreddit;

public class Message {

    String post;
    String poster;
    int upvotes;

    public Message(String post, String poster, int upvotes) {
        this.post = post;
        this.poster = poster;
        this.upvotes = upvotes;
    }

    public Message()
    {

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
}
