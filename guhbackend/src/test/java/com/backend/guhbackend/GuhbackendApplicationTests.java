package com.backend.guhbackend;

import com.backend.guhbackend.gymuser.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GuhbackendApplicationTests {

	private final GymuserController gymUserService;
	private final GymuserRepository gymuserRepository;

	@Autowired
	GuhbackendApplicationTests(GymuserController gymUserService, GymuserRepository gymuserRepository) {
		this.gymUserService = gymUserService;
		this.gymuserRepository = gymuserRepository;
	}

	@Test
	void contextLoads() {

	}

	@Test
	public void testRegisterGymUser() throws GymuserAlreadyRegisteredException {
		GymuserRegistrationRequest request = new GymuserRegistrationRequest("John", "john@gmail.com", LocalDate.of(1990, 1, 1), LocalDate.now(), new HashMap<>());
		gymUserService.registerGymUser(request);
		Optional<Gymuser> gymuserOptional = gymuserRepository.findGymuserByEmail("john@gmail.com");
		assertTrue(gymuserOptional.isPresent());
	}

	@Test
	public void testRegisterGymUserWithAlreadyRegisteredEmail() throws GymuserAlreadyRegisteredException {
		GymuserRegistrationRequest request1 = new GymuserRegistrationRequest("John", "dave@gmail.com", LocalDate.of(1990, 1, 1), LocalDate.now(), new HashMap<>());
		gymUserService.registerGymUser(request1);
		GymuserRegistrationRequest request2 = new GymuserRegistrationRequest("Jane", "dave@gmail.com", LocalDate.of(1995, 1, 1), LocalDate.now(), new HashMap<>());

		GymuserAlreadyRegisteredException exception = assertThrows(GymuserAlreadyRegisteredException.class, () -> gymUserService.registerGymUser(request2));
		assertTrue(exception.getMessage().contains("Email already registered!"));
	}


}
