package com.example.zotreddit;

public class Message {

    String post;
    String poster;
    int upvotes = 0;

    public Message(String post, String poster, int upvotes) {
        this.post = post;
        this.poster = poster;
        this.upvotes = upvotes;
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
