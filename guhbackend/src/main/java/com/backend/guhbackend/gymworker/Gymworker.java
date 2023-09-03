package com.backend.guhbackend.gymworker;

import com.backend.guhbackend.utils.ArithmeticUtils;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gymworker")
public class Gymworker {
    @Id
    @SequenceGenerator(
            name = "gymworker_sequence",
            sequenceName = "gymworker_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gymworker_sequence"
    )

    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String workingType;
    private LocalDate dob;
    private LocalDate startedWork;
    private Integer age;
    private Integer workingTime;

    public Gymworker() {
    }

    public Gymworker(String name,
                     String password, String email,
                     String phone,
                     String workingType,
                     LocalDate dob,
                     LocalDate startedWork) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.workingType = workingType;
        this.dob = dob;
        this.startedWork = startedWork;
    }

    public Gymworker(Long id,
                     String name,
                     String password, String email,
                     String phone,
                     String workingType,
                     LocalDate dob,
                     LocalDate startedWork) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.workingType = workingType;
        this.dob = dob;
        this.startedWork = startedWork;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = ArithmeticUtils.yearCalculator(this.startedWork);
    }

    @Override
    public String toString() {
        return "Gymworker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", workingType='" + workingType + '\'' +
                ", dob=" + dob +
                ", startedWork=" + startedWork +
                ", age=" + age +
                ", workingTime=" + workingTime +
                '}';
    }
}
