package by.bsuir.app.util.indicator;

import by.bsuir.app.entity.Analyzes;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.entity.enums.State;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.List;

public class GeneralBloodIndicatorHandler implements IndicatorHandler<GeneralBloodTest> {

    private final static double ERYTHROCYTES_MIN = 3.8;
    private final static double ERYTHROCYTES_MAX = 4.9;
    private final static double LEUKOCYTES_MIN = 6;
    private final static double LEUKOCYTES_MAX = 17;
    private final static double HEMOGLOBIN_MIN = 110;
    private final static double HEMOGLOBIN_MAX = 146;

    private final String lang;

    public GeneralBloodIndicatorHandler(String lang) {
        this.lang = lang;
    }

    @Override
    public List<GeneralBloodTest> processIndicators(List<? extends Analyzes> analyzes) {
        List<GeneralBloodTest> tests = (List<GeneralBloodTest>) analyzes;

        for (GeneralBloodTest test : tests) {
            test.setErythrocytesState(langResolver(getErythrocytesState(test.getErythrocytes())));
            test.setLeukocytesState(langResolver(getLeukocytesState(test.getErythrocytes())));
            test.setHemoglobinState(langResolver(getHemoglobinState(test.getHemoglobinValue())));
        }

        return tests;
    }

    private String langResolver(State state) {
        return switch (lang) {
            case "en" -> state.getStateEn();
            default -> state.getStateRu();
        };
    }

    private State getErythrocytesState(double value) {
        if (value < ERYTHROCYTES_MIN) {
            return State.LOW;
        } else if (value > ERYTHROCYTES_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }

    private State getLeukocytesState(double value) {
        if (value < LEUKOCYTES_MIN) {
            return State.LOW;
        } else if (value > LEUKOCYTES_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }

    private State getHemoglobinState(double value) {
        if (value < HEMOGLOBIN_MIN) {
            return State.LOW;
        } else if (value > HEMOGLOBIN_MAX) {
            return State.HIGH;
        } else {
            return State.NORMAL;
        }
    }
}
