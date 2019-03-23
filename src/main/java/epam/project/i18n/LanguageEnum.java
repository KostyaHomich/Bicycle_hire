package epam.project.i18n;

import java.util.Locale;

import java.util.Optional;
import java.util.stream.Stream;

public enum LanguageEnum {
    BY,
    RU,
    EN;

    public static Optional<LanguageEnum> fromString(String value) {
        return Stream.of(values()).filter(e -> e.name().equalsIgnoreCase(value)).findFirst();
    }

    public static Locale getLocaleByEnum(LanguageEnum value) {
        switch (value) {
            case BY:
                return new Locale("by", "BY");

            case RU:
                return new Locale("ru", "RU");

            case EN:
                return new Locale("en", "US");

            default:
                return new Locale("en", "US");
        }
    }

}