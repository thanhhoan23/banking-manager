package org.example.model.dto;

import org.example.model.ModelGeneral;

import javax.persistence.Column;

public class CustomerEditDto extends ModelGeneral {
    @Column(name= "full_name")
    private String fullName;
    private String email;
    private String phone;
    private String address;

    public CustomerEditDto() {
    }

    public CustomerEditDto(String fullName, String email, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
