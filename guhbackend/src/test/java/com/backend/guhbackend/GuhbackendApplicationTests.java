package com.backend.guhbackend;

import com.backend.guhbackend.gymuser.Gymuser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

@SpringBootTest
class GuhbackendApplicationTests {

	@Test
	void contextLoads() {

        /*String name,
                   String email,
                   LocalDate dob,
                   LocalDate registrationDate,
                   HashMap<Integer, LocalDate> purchaseDateMap*/

		LocalDate dob = LocalDate.of(2002, Month.JUNE, 8);
		String name = "david";
		String email = "david@gmail.com";
		LocalDate registrationDate = LocalDate.of(2022, Month.AUGUST, 15);
		HashMap<Integer, LocalDate> purchaseDateMap = new HashMap<>();
		purchaseDateMap.put(30, LocalDate.of(2023, Month.AUGUST, 26));


		Gymuser gymuser = new Gymuser(name, email, dob, registrationDate, purchaseDateMap);

		System.out.println("\n\n##############################");
		System.out.println(gymuser);
		System.out.println(gymuser.getAge());
		System.out.println(gymuser.getDaysAllowed());
		System.out.println("##############################\n\n");
	}

}
