package com.backend.guhbackend;

import com.backend.guhbackend.gymuser.Gymuser;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GuhbackendApplicationTests {
    @Test
    public void testSetAge_PositiveCase() {
        // Arrange
        LocalDate dob = LocalDate.of(1990, 5, 15);
        Gymuser gymuser = new Gymuser();

        // Act
        gymuser.setDob(dob);
        gymuser.setAge();

        // Assert
        assertEquals(33, gymuser.getAge());
    }

    @Test
    public void testSetAge_NegativeCase() {
        // Arrange
        LocalDate dob = LocalDate.of(2002, 1, 1);
        Gymuser gymuser = new Gymuser();

        // Act
        gymuser.setDob(dob);
        gymuser.setAge();

        // Assert
        assertEquals(21, gymuser.getAge());
    }

    @Test
    public void testSetDaysAllowed() {
        // Arrange
        LocalDate purchaseDate1 = LocalDate.of(2023, 9, 1);
        LocalDate purchaseDate2 = LocalDate.of(2023, 9, 6);
        LinkedHashMap<Integer, LocalDate> purchaseDateMap = new LinkedHashMap<>();
        purchaseDateMap.put(1, purchaseDate1);
        purchaseDateMap.put(3, purchaseDate2);
        Gymuser gymuser = new Gymuser();

        // Act
        gymuser.setPurchaseDateMap(purchaseDateMap);
        gymuser.setDaysAllowed();

        // Assert
        assertEquals(2, gymuser.getDaysAllowed());
    }
}
