package com.github.space125.jrtb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavarushTelegramBotApplicationTests {
	static{
		String token = System.getenv("token");
		System.out.println(token);
	}
	@Test
	void contextLoads() {
	}

}
