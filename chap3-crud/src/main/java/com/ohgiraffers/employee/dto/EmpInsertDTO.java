package com.ohgiraffers.employee.dto;

public class EmpInsertDTO {
    private String empId;
    private String empName;
    private String empNo;
    private String salLevel;
    private String jobCode;

    public EmpInsertDTO(String empId, String empName, String empNo, String salLevel, String jobCode) {
        this.empId = empId;
        this.empName = empName;
        this.empNo = empNo;
        this.salLevel = salLevel;
        this.jobCode = jobCode;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getSalLevel() {
        return salLevel;
    }

    public void setSalLevel(String salLevel) {
        this.salLevel = salLevel;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    @Override
    public String toString() {
        return "EmpInsertDTO{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", empNo='" + empNo + '\'' +
                ", salLevel='" + salLevel + '\'' +
                ", jobCode='" + jobCode + '\'' +
                '}';
    }
}
