package com.example.InventoryManagement.LearningBeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("diwali")
@Primary
public class DiwaliGreetings implements Greetings{

	@Value("${festival.diwali.items}")
	private String items;
	
	@Override
	public String greetings() {
		return "Happy Diwali" + " " + items;
	}

}
