package org.example.model.dto;

import org.example.model.ModelGeneral;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;

public class CustomerEditDto implements Validator {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerEditDto customerEditDto = (CustomerEditDto) target;
        String fullName = customerEditDto.getFullName();
        String email = customerEditDto.getEmail();
        String phone = customerEditDto.getPhone();
        String address = customerEditDto.getAddress();

        if (fullName == null){
            errors.rejectValue("fullName", "fullName", "Please input the name");
            return;
        } else if (fullName.trim().isEmpty()) {
            errors.rejectValue("fullName", "fullName", "The full Name can not empty");
            return;
        } else if (fullName.length()<5 || fullName.length()>30){
            errors.rejectValue("fullName","fullName.length", "The full name must be between 5 and 30" );
            return;
        }
        if(email.trim().isEmpty()) {
            errors.rejectValue("email", "email", "Email can not empty");
            return;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches", "Email not valid");
            return;
        }

        if(phone.trim().isEmpty()){
            errors.rejectValue("phone", "phone", "Phone can not empty");
            return;
        }
        if(!phone.matches("\\b(84|0)([3|5|7|8|9])([0-9]{8})\\b")) {
            errors.rejectValue("phone","phone.matches", "Number phone not valid");
            return;
        }
        if(address.trim().isEmpty()){
            errors.rejectValue("address", "address", "address can not empty");
        }
    }
}
