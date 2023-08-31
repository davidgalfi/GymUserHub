package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.gymuser.utils.ArithmeticUtils;
import com.backend.guhbackend.gymuser.utils.ConvertingClass;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;

@Entity // Telling Java that this class is working with database
@Table(name="gymuser") // Setting the table
public class Gymuser {
    @Id // All rows have to be uniquely identify
    @SequenceGenerator(
            name = "sequence_gymuser",
            sequenceName = "sequence_gymuser",
            allocationSize = 1 // Tells how many Id being generated in one call
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_gymuser"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob; // Date of Birth
    private LocalDate registrationDate;
    @Convert(converter = ConvertingClass.class)
    private HashMap<Integer, LocalDate> purchaseDateMap;
    @Transient // Table does not contain this data
    private Integer daysAllowed;
    @Transient // Table does not contain this data
    private Integer age;
    @Transient // Table does not contain this data
    private Integer discount;

    public Gymuser() {

    }

    public Gymuser(String name,
                   String email,
                   LocalDate dob,
                   LocalDate registrationDate) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.registrationDate = registrationDate;
        setAge();
    }

    public Gymuser(String name,
                   String email,
                   LocalDate dob,
                   LocalDate registrationDate,
                   HashMap<Integer, LocalDate> purchaseDateMap) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.registrationDate = registrationDate;
        this.purchaseDateMap = purchaseDateMap;
        setAge();
        setDaysAllowed();
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

    public HashMap<Integer, LocalDate> getPurchaseDateMap() {
        return purchaseDateMap;
    }

    public void setPurchaseDateMap(HashMap<Integer, LocalDate> purchaseDateMap) {
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
        this.age = ArithmeticUtils.ageCalculate(dob);
    }

    public Integer getDiscount() {
        return discount;
    }

    // TODO: Create a way to calculate discount
    public void setDiscount(Byte discount) {
        this.discount = Integer.valueOf(discount);
    }

    @Override
    public String toString() {
        return "Gym-user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", registrationDate=" + registrationDate +
                ", purchaseDateMap=" + purchaseDateMap +
                ", daysAllowed=" + daysAllowed +
                ", age=" + age +
                ", discount=" + discount +
                '}';
    }
}
