package ru.netology.i18n;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {


    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @ParameterizedTest()
    @CsvSource({
            "RUSSIA, Добро пожаловать",
            "USA, Welcome",
            "GERMANY, Welcome"
    })
    public void testLocale(Country country, String expectedMessage) {
        String actualMessage = localizationService.locale(country);
        assertEquals(expectedMessage, actualMessage);
    }

}