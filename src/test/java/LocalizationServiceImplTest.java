import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {


    @Test
    public void testLocaleRussia() {
        LocalizationService localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        String messageRussia = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", messageRussia);
        verify(localizationService).locale(Country.RUSSIA);
    }
    @Test
    public void testLocaleUSA() {
        LocalizationService localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        String messageUSA = localizationService.locale(Country.USA);
        assertEquals("Welcome", messageUSA);
        verify(localizationService).locale(Country.USA);
    }

}