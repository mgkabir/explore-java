package io.explore.stream;

import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamCollector {

    public static void main(String... args) {

        DecimalFormat df = new DecimalFormat("#,###.00");
        Stream.generate(() -> Math.random() * 10000).limit(10)
                /* do partition on the data stream */
                .collect(Collectors.partitioningBy(element -> element > 5000))
                .forEach((checkPoint, list) -> {
                    System.out.print(checkPoint ? "\tLarger Partition:" : "\tSmaller Partition:");
                    /*collect statistics of each partition*/
                    DoubleSummaryStatistics stats = list.stream().collect(Collectors.summarizingDouble(e -> e));

                    System.out.println("\tsum : " + df.format(stats.getSum()) + "\tcount : " + stats.getCount()
                            + " \tavg : " + df.format(stats.getAverage()) + "\tmin : " + df.format(stats.getMin())
                            + "\tmax : " + df.format(stats.getMax()));
                });
    }

}
