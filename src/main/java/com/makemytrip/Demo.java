package com.makemytrip;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Demo {
	
	public static void main(String[] args) {
		
		LocalDate date = LocalDate.now();

		//date = date.plusDays(17);
		
		System.out.println(date);
		System.out.println(date.getDayOfMonth());
		System.out.println(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
		System.out.println(date.getYear());
	}

}
