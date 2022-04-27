package by.bsuir.app.util.indicator;

import by.bsuir.app.entity.Analyzes;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.entity.enums.State;

import java.util.List;

public class BiochemicalBloodIndicatorHandler implements IndicatorHandler<BiochemicalBloodTest>{

    private final static double PROTEIN_MIN = 60;
    private final static double PROTEIN_MAX = 85;
    private final static double GLUCOSE_MIN = 3.8;
    private final static double GLUCOSE_MAX = 6.3;
    private final static double CHOLESTEROL_MIN = 3.5;
    private final static double CHOLESTEROL_MAX = 5.5;

    private final String lang;

    public BiochemicalBloodIndicatorHandler(String lang) {
        this.lang = lang;
    }

    @Override
    public List<BiochemicalBloodTest> processIndicators(List<? extends Analyzes> analyzes) {
        List<BiochemicalBloodTest> tests = (List<BiochemicalBloodTest>) analyzes;

        for (BiochemicalBloodTest test : tests) {
            test.setProteinState(langResolver(getProteinState(test.getProtein())));
            test.setGlucoseState(langResolver(getGlucoseState(test.getGlucose())));
            test.setCholesterolState(langResolver(getCholesterolState(test.getCholesterol())));
        }

        return tests;
    }

    private String langResolver(State state) {
        return switch (lang) {
            case "en" -> state.getStateEn();
            default -> state.getStateRu();
        };
    }

    private State getProteinState(double value) {
        if (value < PROTEIN_MIN) {
            return State.LOW;
        } else if (value > PROTEIN_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }

    private State getGlucoseState(double value) {
        if (value < GLUCOSE_MIN) {
            return State.LOW;
        } else if (value > GLUCOSE_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }

    private State getCholesterolState(double value) {
        if (value < CHOLESTEROL_MIN) {
            return State.LOW;
        } else if (value > CHOLESTEROL_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }
}
