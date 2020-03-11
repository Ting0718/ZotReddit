package com.example.zotreddit;

public class Reply {

    String post;
    String poster;
    int upvotes;

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


