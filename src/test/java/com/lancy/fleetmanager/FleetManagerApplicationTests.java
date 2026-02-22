package com.lancy.fleetmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles; // <--- ADD THIS

@SpringBootTest
@ActiveProfiles("test") // <--- ADD THIS to force H2
class FleetManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
