package com.learning.springboot.dto;

public class Response {
    public Response() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Response(Boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    private Boolean success;
    private String message;
    private User user;
}
