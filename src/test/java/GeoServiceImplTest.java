import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.entity.Location;
import ru.netology.entity.Country;

public class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    public void testByIpLocalhost() {
        Location location = geoService.byIp("127.0.0.1");
        assertNull(location.getCity());
        assertNull(location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIpMoscowDefaultIp() {
        Location location = geoService.byIp("172.0.32.11");
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }

    @Test
    public void testByIpNewYorkDefaultIp() {
        Location location = geoService.byIp("96.44.183.149");
        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertEquals(" 10th Avenue", location.getStreet());
        assertEquals(32, location.getBuiling());
    }

    @Test
    public void testByIpMoscowRange() {
        Location location = geoService.byIp("172.16.0.1");
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testByIpNewYorkRange() {
        Location location = geoService.byIp("96.0.0.1");
        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void testRandomIp() {
        Location location = geoService.byIp("8.8.8.8");
        assertNull(location);
    }
}