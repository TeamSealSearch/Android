package com.example.capstone;

// https://stackoverflow.com/questions/22288659/how-can-i-parse-pdf-file-in-android

public class Posting {
    private String jobTitle;
    private String qualifications;
    private String company;
    private String expLevel;

    Posting() {
    }

    Posting (String t, String q, String c, String e) {
        jobTitle = t;
        qualifications = q;
        company = c;
        expLevel = e;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getCompany() {
        return company;
    }

    public String getJobDescription() {
        return "This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description  This is a long description ";
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }
}
