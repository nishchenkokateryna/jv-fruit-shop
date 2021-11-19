package core.basesyntax.service.impl;

import core.basesyntax.model.Fruit;
import core.basesyntax.service.ReportMaker;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportMakerImpl implements ReportMaker {
    private static final String HEAD_OF_REPORT = "fruit,quantity";
    private static final String COMMA = ",";

    @Override
    public String makeReport(List<Fruit> fruits) {
        Map<String, Long> totalAmount = totalAmountCalculator(fruits);
        StringBuilder reportBuilder = new StringBuilder(HEAD_OF_REPORT);
        for (Map.Entry<String, Long> entry : totalAmount.entrySet()) {
            reportBuilder.append(System.lineSeparator())
                    .append(entry.getKey())
                    .append(COMMA)
                    .append(entry.getValue());
        }
        return reportBuilder.toString();
    }

    private Map<String, Long> totalAmountCalculator(List<Fruit> fruits) {
        return fruits.stream()
                .map(Fruit::getName)
                .collect(Collectors.groupingBy(Function.identity(), (Collectors.counting())));
    }
}
