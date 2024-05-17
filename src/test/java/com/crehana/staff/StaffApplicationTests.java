package com.crehana.staff;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StaffApplicationTests {

	private final ApplicationContext applicationContext;

    StaffApplicationTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	void testEmployeeServiceBeanExists() {
		boolean beanExists = applicationContext.containsBean("employeeService");
		assertThat(beanExists).isTrue();
	}

}
