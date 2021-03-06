package com.meest.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  RegisterResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Data {

        @SerializedName("newUser")
        @Expose
        private NewUser newUser;
        @SerializedName("token")
        @Expose
        private String token;

        public NewUser getNewUser() {
            return newUser;
        }

        public void setNewUser(NewUser newUser) {
            this.newUser = newUser;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }


    public class NewUser {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("likes")
        @Expose
        private Integer likes;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
