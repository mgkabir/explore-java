package io.explore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Java8DataTime {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate nextTuesday = today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		System.out.println("Next Tuesday on : " + nextTuesday);
	}

}
