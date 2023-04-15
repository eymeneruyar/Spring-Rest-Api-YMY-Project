package com.work.ymy.company.payload.request;

import lombok.Data;

@Data
public class CompanyRequest {
    private long userId;
    private String code;
    private String name;
    private String authorisedPerson;
    private String phone;
    private String email;
    private String taxOffice;
    private String taxNumber;
    private String city;
    private String town;
    private String address;
}
