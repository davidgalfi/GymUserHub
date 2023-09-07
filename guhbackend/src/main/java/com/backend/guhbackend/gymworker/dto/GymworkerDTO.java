package com.backend.guhbackend.gymworker.dto;

import com.backend.guhbackend.utils.ArithmeticUtils;

import java.time.LocalDate;

public class GymworkerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String workingType;
    private LocalDate dob;
    private LocalDate startedWork;
    private Integer age;
    private Integer workingTime;

    public GymworkerDTO(Long id,
                        String name,
                        String email,
                        String phone,
                        String workingType,
                        LocalDate dob,
                        LocalDate startedWork) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.workingType = workingType;
        this.dob = dob;
        this.startedWork = startedWork;
        setAge();
        setWorkingTime();
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

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getStartedWork() {
        return startedWork;
    }

    public void setStartedWork(LocalDate startedWork) {
        this.startedWork = startedWork;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        this.age = ArithmeticUtils.ageCalculate(this.dob);
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime() {
        this.workingTime = ArithmeticUtils.yearCalculator(this.startedWork);
    }

}
