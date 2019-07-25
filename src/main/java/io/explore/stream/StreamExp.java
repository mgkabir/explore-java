package io.explore.stream;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExp {
    public static void main(String[] args) {
        infiniteStream();
    }

    private static void infiniteStream() {
        Supplier<UUID> randomUUIDSupplier = UUID::randomUUID;
        Stream<UUID> infiniteStreamOfUUID = Stream.generate(randomUUIDSupplier);

        List<UUID> listOfUUID = infiniteStreamOfUUID
                .skip(10)
                .limit(5)
                .collect(Collectors.toList());
        listOfUUID.forEach(System.out::println);
    }
}
