package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Location;


public class LocationUnitTest {

    @Test
    public void setLatitudeTest() throws NoSuchFieldException, IllegalAccessException {
        Float value = 506f;
        Location location = new Location();
        location.setLatitude(value);
        Field field = location.getClass().getDeclaredField("latitude");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(location), value);
    }

    @Test
    public void getLatitudeTest() throws NoSuchFieldException, IllegalAccessException {
        Location location = new Location();
        Field field = location.getClass().getDeclaredField("latitude");
        field.setAccessible(true);
        field.set(location, 506.2f);

        Float result = location.getLatitude();
        assertTrue(result == 506.2f);
    }

    @Test
    public void setLongitudeTest() throws NoSuchFieldException, IllegalAccessException {
        Float value = 506f;
        Location location = new Location();
        location.setLongitude(value);
        Field field = location.getClass().getDeclaredField("longitude");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(location), value);
    }

    @Test
    public void getLongitudeTest() throws NoSuchFieldException, IllegalAccessException {
        Location location = new Location();
        Field field = location.getClass().getDeclaredField("longitude");
        field.setAccessible(true);
        field.set(location, 506.2f);

        Float result = location.getLongitude();
        assertTrue(result == 506.2f);
    }
}
