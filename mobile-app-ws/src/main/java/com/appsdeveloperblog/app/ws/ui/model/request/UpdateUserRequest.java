package com.appsdeveloperblog.app.ws.ui.model.request;

import javax.validation.constraints.NotNull;

public class UpdateUserRequest {
    private String first;
    @NotNull(message = "Last name must not be null")
    private String lastName;
    
    public String getFirstName() {
        return first;
    }
    public void setFirstName(String firstName) {
        this.first = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
