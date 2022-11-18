package com.projectname.api.client.data.model.users.create;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Model of response created with online tool http://www.jsonschema2pojo.org/
public class CreateUserResponse implements Serializable
{

    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;
    @SerializedName("id")
    private String id;
    @SerializedName("createdAt")
    private String createdAt;
    private final static long serialVersionUID = 1114216090953894920L;

    public CreateUserResponse() {
    }

    public CreateUserResponse(String name, String job, String id, String createdAt) {
        super();
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    //We will use this method to build an expected response as a Java Object in order to compare it with the actual response
    public static CreateUserResponse parseCreatedUser(CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setName(createUserRequest.getName());
        createUserResponse.setJob(createUserRequest.getJob());
        return createUserResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

