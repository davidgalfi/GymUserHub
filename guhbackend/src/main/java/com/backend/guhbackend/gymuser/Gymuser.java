package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.utils.ArithmeticUtils;
import com.backend.guhbackend.utils.CollectionFunctions;
import com.backend.guhbackend.utils.ConvertingClass;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name="gymuser")
public class Gymuser {
    @Id
    @SequenceGenerator(
            name = "sequence_gymuser",
            sequenceName = "sequence_gymuser",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_gymuser"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private LocalDate registrationDate;
    @Convert(converter = ConvertingClass.class)
    private LinkedHashMap<Integer, LocalDate> purchaseDateMap;
    @Transient
    private Integer daysAllowed;
    @Transient
    private Integer age;
    @Transient
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
                   LinkedHashMap<Integer, LocalDate> purchaseDateMap) {
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

    public LinkedHashMap<Integer, LocalDate> getPurchaseDateMap() {
        Optional<Map.Entry<Integer, LocalDate>> purchaseDateOptional =
                CollectionFunctions.getLastEntry(this.purchaseDateMap);
        LinkedHashMap<Integer, LocalDate> purchaseDate = new LinkedHashMap<Integer, LocalDate>();
        purchaseDateOptional.ifPresent(entry -> purchaseDate.put(entry.getKey(), entry.getValue()));
        return purchaseDate;
    }
    public LinkedHashMap<Integer, LocalDate> getAllPurchaseDateMap() {
        return this.purchaseDateMap;
    }
    public void setPurchaseDateMap(LinkedHashMap<Integer, LocalDate> purchaseDateMap) {
        if(this.purchaseDateMap != null){
            this.purchaseDateMap.putAll(purchaseDateMap);
        } else {
            this.purchaseDateMap = purchaseDateMap;
        }
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
    public void setDiscount(Integer discount) {
        this.discount = discount;
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
