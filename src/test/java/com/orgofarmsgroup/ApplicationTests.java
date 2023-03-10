package com.orgofarmsgroup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ApplicationTests {

	@Test
	@DisplayName(value = "root context load")
	void contextLoads() {
		log.info("loading context");
	}

}
