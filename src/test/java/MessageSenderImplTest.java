import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

public class MessageSenderImplTest {

    private GeoService geoServiceMock;
    private LocalizationService localizationServiceMock;
    private MessageSenderImpl messageSender;

    @BeforeEach
    public void setUp() {
        geoServiceMock = mock(GeoService.class);
        localizationServiceMock = mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
    }

    @Test
    public void testRussianMessage() {
        //arrange
        String ip = "172.0.32.11";
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);

        // Мокаем geoService и localizationService
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        when(geoServiceMock.byIp(ip)).thenReturn(location);
        when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        //act
        String result = messageSender.send(headers);

        //assert
        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void DefaultEnglishMessage() {
        //arrange
        Map<String, String> headers = new HashMap<>();
        // Мокаем localizationService
        when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        //act
        String result = messageSender.send(headers);

        //assert
        assertEquals("Welcome", result);
    }

    @Test
    public void testEnglishMessage() {
        //arrange
        String ip = "96.44.183.149";
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);

        // Мокаем geoService и localizationService
        Location location = new Location("New York", Country.USA, "10th Avenue", 32);
        when(geoServiceMock.byIp(ip)).thenReturn(location);
        when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        //act
        String result = messageSender.send(headers);

        //assert
        assertEquals("Welcome", result);
    }

    @Test
    public void testRussianNoDefaultLocation() {
        //arrange
        String ip = "172.1.22.123";
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);

        // Мокаем geoService и localizationService
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        when(geoServiceMock.byIp(ip)).thenReturn(location);
        when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        //act
        String result = messageSender.send(headers);

        //assert
        assertEquals("Добро пожаловать", result);
    }
}