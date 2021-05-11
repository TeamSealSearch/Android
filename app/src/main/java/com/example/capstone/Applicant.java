package com.example.capstone;

public class Applicant {
    private String username;
    private String fname;
    private String lname;

    private int yearsOfExp;
    private int problemSolving;
    private int tech_degree;
    private int jobType;
    private int growthOpp;
    private int consistency;
    private int communication;
    private int leadership;

    public Applicant () {

    }

    public Applicant(String u, String f, String l, int yoE, int prob, int deg, int jobT, int gr, int con, int com, int lead) {
        username = u;
        fname = f;
        lname = l;

        yearsOfExp = yoE;
        problemSolving = prob;
        tech_degree = deg;
        jobType = jobT;
        growthOpp = gr;
        consistency = con;
        communication = com;
        leadership = lead;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public int getProblemSolving() {
        return problemSolving;
    }

    public void setProblemSolving(int problemSolving) {
        this.problemSolving = problemSolving;
    }

    public int getTech_degree() {
        return tech_degree;
    }

    public void setTech_degree(int tech_degree) {
        this.tech_degree = tech_degree;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public int getGrowthOpp() {
        return growthOpp;
    }

    public void setGrowthOpp(int growthOpp) {
        this.growthOpp = growthOpp;
    }

    public int getConsistency() {
        return consistency;
    }

    public void setConsistency(int consistency) {
        this.consistency = consistency;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }
}
