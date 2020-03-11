package com.example.zotreddit;

public class Reply {

    String post;
    String poster;
    int upvotes;
    String key;

    public Reply(String poster, String post, int upvotes, String key) {
        this.poster = poster;
        this.post = post;
        this.upvotes = upvotes;
        this.key = key;
    }


    public Reply()
    {

    }

    public String getKey() {
        return key;
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


