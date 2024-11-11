package org.gradle.fins.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

// NOTE
// JsonProperty : Needed when variable name is not in Java naming convention (camelCase)

@Entity
@Table(name = "TCB_FINS_USER")
public class UserModel {

    @Id
    private String USER_ID;
    private String USER_NAME;

    public String getUserId() {
        return this.USER_ID;
    }
    public String getUserName() {
        return this.USER_NAME;
    }

    public void setUserId(String userId) {
        this.USER_ID = userId;
    }
    public void setUserName(String userName) {
        this.USER_NAME = userName;
    }
}