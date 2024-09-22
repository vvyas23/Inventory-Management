package com.example.InventoryManagement.LearningBeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("ChristmasGreetings1")
@Profile("christmas")
public class ChristmasGreetings implements Greetings{

	@Value("${festival.christmas.items}")
	private String items;
	
	@Override
	public String greetings() {
		return "Merry Christmas " + items;
	}
}
