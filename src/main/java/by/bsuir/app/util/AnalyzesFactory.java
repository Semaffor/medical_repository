package by.bsuir.app.util;

import by.bsuir.app.entity.Analyzes;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.entity.GeneralBloodTest;

public class AnalyzesFactory {

    private static AnalyzesFactory analyzesFactory;
    private AnalyzesFactory() {}

    public static AnalyzesFactory getInstance() {
        if (analyzesFactory == null) {
            analyzesFactory = new AnalyzesFactory();
        }
        return analyzesFactory;
    }

    public Analyzes<?> getAnalyze(AnalyzesEnum analyzesEnum) {
        return switch (analyzesEnum) {
            case BIO -> new BiochemicalBloodTest();
            case GENERAL-> new GeneralBloodTest();
        };
    }
}
