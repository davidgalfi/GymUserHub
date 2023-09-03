package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.utils.ArithmeticUtils;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class GymuserDTO{
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private LocalDate registrationDate;
    private LinkedHashMap<Integer, LocalDate> purchaseDateMap;
    private Integer daysAllowed;
    private Integer age;

    public GymuserDTO(Long id,
                      String name,
                      String email,
                      LocalDate dob,
                      LocalDate registrationDate,
                      LinkedHashMap<Integer, LocalDate> purchaseDateMap) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.registrationDate = registrationDate;
        this.purchaseDateMap = purchaseDateMap;
        setDaysAllowed();
        setAge();
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LinkedHashMap<Integer, LocalDate> getPurchaseDateMap() {
        return purchaseDateMap;
    }

    public void setPurchaseDateMap(LinkedHashMap<Integer, LocalDate> purchaseDateMap) {
        this.purchaseDateMap = purchaseDateMap;
    }

    public Integer getDaysAllowed() {
        return daysAllowed;
    }

    public void setDaysAllowed() {
        this.daysAllowed = ArithmeticUtils.daysAllowedToUseGym(this.purchaseDateMap);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        this.age = ArithmeticUtils.ageCalculate(this.dob);
    }
}
