package ru.moscowzoo.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;
import ru.moscowzoo.clinic.HealthInspection;
import ru.moscowzoo.clinic.InspectionReport;
import ru.moscowzoo.clinic.VeterinaryClinic;
import ru.moscowzoo.io.ConsoleGateway;
import ru.moscowzoo.zoo.Zoo;

class ZooConsoleTest {
    @Test
    void fullScenarioAddsAnimalThingAndReports() {
        StubConsole console = new StubConsole(
                "1", "rabbit", "Зая", "4", "7",
                "2", "table", "Стол",
                "3",
                "4",
                "5",
                "0");
        Zoo zoo = new Zoo(new VeterinaryClinic(alwaysAccept()));
        ZooConsole app = new ZooConsole(console, zoo);

        app.run();

        assertEquals(1, zoo.getAnimals().size());
        assertEquals(2, zoo.getInventory().size());
        assertTrue(console.getOutput().stream().anyMatch(line -> line.contains("Суммарный расход еды")));
    }

    private HealthInspection alwaysAccept() {
        return animal -> InspectionReport.accepted("ok");
    }

    private static final class StubConsole implements ConsoleGateway {
        private final Queue<String> input;
        private final List<String> output = new ArrayList<>();

        private StubConsole(String... values) {
            input = new ArrayDeque<>(List.of(values));
        }

        @Override
        public void println(String message) {
            output.add(message);
        }

        @Override
        public String readLine() {
            return input.poll();
        }

        List<String> getOutput() {
            return output;
        }

        @Override
        public void close() {
            // nothing to close
        }
    }
}
