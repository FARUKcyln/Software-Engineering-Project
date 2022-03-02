package com.example.mapapp.connector;

public class PostFeedResponse {
    String errorText;
    Boolean status;
    FeedResponse feed;

    public class FeedResponse {
        String feed;

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public FeedResponse getFeed() {
        return feed;
    }

    public void setFeed(FeedResponse feed) {
        this.feed = feed;
    }
}
