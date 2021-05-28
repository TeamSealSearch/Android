package com.example.capstone;

public class CompanyProfile {
    private String name;
    private String industry;

    CompanyProfile() {

    }

    CompanyProfile(String n, String i) {
        name = n;
        industry = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
