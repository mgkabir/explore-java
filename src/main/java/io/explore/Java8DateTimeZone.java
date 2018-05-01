package io.explore;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Java8DateTimeZone {

	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds()
			.stream()
			.filter(zoneId -> zoneId.contains("Aus"))
			.sorted()
			.limit(5)
			.forEach(System.out::println);
		
		System.out.println("Default Time Zone : "+ZoneId.systemDefault());
		
		ZoneId dhakaZoneId = ZoneId.of("Asia/Dhaka");
		ZonedDateTime zdt = ZonedDateTime.of(LocalDate.now(dhakaZoneId), LocalTime.now(dhakaZoneId), dhakaZoneId);
		System.out.println("ZonedDateTime of "+dhakaZoneId+" : "+zdt);
		System.out.println("Zone Offeset : "+zdt.getOffset());
		
		
		ZoneId adelaideZone = ZoneId.of("Australia/Adelaide");
		System.out.println(adelaideZone+" DST is : "+adelaideZone.getRules().getDaylightSavings(Instant.now()).toHours()); 
		
		ZoneId brisbaneZone = ZoneId.of("Australia/Brisbane");
		System.out.println(brisbaneZone+" DST is : "+brisbaneZone.getRules().getDaylightSavings(Instant.now()).toHours()); 
	}

}
