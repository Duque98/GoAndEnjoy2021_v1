package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.District;
import es.unex.goenjoy.model.Museo;

public class DistrictUnitTest {

    @Test
    public void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        String value = "506";
        District district = new District();
        district.setId(value);
        Field field = district.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(district), value);
    }

    @Test
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        District district = new District();
        Field field = district.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(district, "506");

        String result = district.getId();
        assertEquals("Field wasn't retrieved properly", result, "506");
    }
}
