package com.example.IntegrationAPI.Dto;



public class EmployeeUser {
    private Long UserId;
    private String empCode;
    private String lastname;
    private String department;


    public EmployeeUser( Long UserId,  String empCode,String lastname,  String department) {
        this.UserId = UserId;
        this.empCode=empCode;
        this.lastname = lastname;
        this.department = department;


    }

    public EmployeeUser() {
    }

    public Long getUserId() {
        return UserId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }
}
