package com.mytel.taskmanagementtool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "TMS_EMPLOYEE",uniqueConstraints = @UniqueConstraint(columnNames = {"user_name"}))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq",allocationSize = 1,sequenceName = "employee_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "position")
    private String position;

    @Column(name = "vmy_code")
    private String vmyCode;

    @Column(name = "department")
    private String department;

    @Column(name = "city")
    private String city;

    public Employee(){

    }
    public Employee(Long id, String name, String userName, String phoneNumber, String position, String vmyCode, String department, String city) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.vmyCode = vmyCode;
        this.department = department;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVmyCode() {
        return vmyCode;
    }

    public void setVmyCode(String vmyCode) {
        this.vmyCode = vmyCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

