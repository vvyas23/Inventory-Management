package com.example.InventoryManagement.actuatorServices;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class Service2HealthIndicator implements HealthIndicator{

	@Override
	public Health health() {
		int errorCode = check();
		if(errorCode != 0) {
			return Health.outOfService().withDetail("ErrorCode", errorCode).build();
		}
		return Health.up().withDetail("Success", 0).build();
	}
	
	private int check() {
		return 0;
	}

}
