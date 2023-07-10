package com.gopal.serviceRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoneyWorkflowApplication {

	@Test
	void contextLoads() {
		
		SimpleDateFormat sd = new SimpleDateFormat(
	            "yyyy.MM.dd G 'at' HH:mm:ss z");
	        Date date = new Date();
	        // TODO: Avoid using the abbreviations when fetching time zones.
	        // Use the full Olson zone ID instead.
	        sd.setTimeZone(TimeZone.getTimeZone("IST"));
	        System.out.println(sd.format(date));
	   
	}

}
