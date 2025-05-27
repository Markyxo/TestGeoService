package ru.netology.geo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Location;
import ru.netology.entity.Country;

public class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @ParameterizedTest()
    @CsvSource({
            "127.0.0.1, , , ,0",
            "172.0.32.11, Moscow, RUSSIA, Lenina, 15",
            "96.44.183.149, New York, USA, 10th Avenue, 32"
    })
    public void testDefaultIp(String ip, String expectedCity, Country expectedCountry, String expectedStreet, int expectedBuilding) {
        Location location = geoService.byIp(ip);

        assertEquals(expectedCity, location.getCity());
        assertEquals(expectedCountry, location.getCountry());
        assertEquals(expectedStreet, location.getStreet());
        assertEquals(expectedBuilding, location.getBuiling());
    }

    @ParameterizedTest()
    @CsvSource({
            "172.16.0.1, Moscow, RUSSIA",
            "96.0.0.1, New York, USA",
            "172.12.2.41, Moscow, RUSSIA",
            "96.312.1.31, New York, USA"
    })

    public void testIpRange(String ip, String expectedCity, Country expectedCountry) {
        Location location = geoService.byIp(ip);
        assertEquals(expectedCity, location.getCity());
        assertEquals(expectedCountry, location.getCountry());
    }


    @Test
    public void testUnknownIp() {
        Location location = geoService.byIp("8.8.8.8");
        assertNull(location);
    }
}