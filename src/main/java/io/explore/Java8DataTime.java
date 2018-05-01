package io.explore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Java8DataTime {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate nextTuesday = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		System.out.println("Next Tuesday on : " + nextTuesday);
		System.out.println("Formatted Time  : " +DateTimeFormatter.ISO_TIME.format(LocalTime.now()));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		System.out.println("Custom formatted Next Tuesday : "+ formatter.format(nextTuesday));
	}

}
