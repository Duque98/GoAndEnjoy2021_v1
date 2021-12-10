package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Area;

public class AreaUnitTest {

    @Test
    public void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        String value = "506";
        Area area = new Area();
        area.setId(value);
        Field field = area.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(area), value);
    }

    @Test
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        Area area = new Area();
        Field field = area.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(area, "506");

        String result = area.getId();
        assertEquals("Field wasn't retrieved properly", result, "506");
    }
}
