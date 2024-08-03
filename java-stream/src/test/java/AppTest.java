import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class AppTest {
    @Test
    void createEmptyOfSingleStream() {
        Stream<String> emptyStream = Stream.empty();
        Stream<String> stringStream = Stream.of("Hello");
        Stream<String> emptyOrNullable = Stream.ofNullable(null);
    }

    @Test
    void createArrayStream() {
        Stream<String> stringStream = Stream.of("Hello", "World");
        stringStream.forEach(System.out::println);

        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        integerStream.forEach(System.out::println);

        String[] strings = {"Hello", "World"};
        Stream<String> arrayStream = Arrays.stream(strings);
        arrayStream.forEach(System.out::println);
    }

    @Test
    void streamFromCollection() {
        Collection<String> collection = List.of("Oyen", "Snowy", "Chiko");
        Stream<String> stream = collection.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void createInfiniteStream() {
        Stream<String> infiniteStream = Stream.generate(() -> "Oyen");
        // infiniteStream.forEach(System.out::println);

        Stream<Integer> iterate = Stream.iterate(1, i -> i + 1);
        // iterate.forEach(System.out::println);
    }
}
