package ru.moscowzoo.clinic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;
import ru.moscowzoo.domain.animal.Tiger;
import ru.moscowzoo.io.ConsoleGateway;

class InteractiveHealthInspectionTest {
    @Test
    void acceptsPositiveAnswer() {
        StubConsole console = new StubConsole(List.of("y"));
        InteractiveHealthInspection inspection = new InteractiveHealthInspection(console);

        InspectionReport report = inspection.inspect(new Tiger("Амур", 6, 1));
        assertTrue(report.isAccepted());
    }

    @Test
    void rejectsNegativeAnswer() {
        StubConsole console = new StubConsole(List.of("n"));
        InteractiveHealthInspection inspection = new InteractiveHealthInspection(console);

        InspectionReport report = inspection.inspect(new Tiger("Амур", 6, 1));
        assertFalse(report.isAccepted());
    }

    private static final class StubConsole implements ConsoleGateway {
        private final Queue<String> inputs;
        private final List<String> outputs = new ArrayList<>();

        private StubConsole(List<String> inputs) {
            this.inputs = new ArrayDeque<>(inputs);
        }

        @Override
        public void println(String message) {
            outputs.add(message);
        }

        @Override
        public String readLine() {
            return inputs.poll();
        }
    }
}
