package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Location;
import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.model.Organization;


public class MuseoUnitTest {

    @Test
    public void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        int value =506;
        Museo museo = new Museo();
        museo.setId(value);
        Field field = museo.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), value);
    }

    @Test
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Field field = museo.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(museo, 506);

        int result = museo.getId();
        assertEquals("Field wasn't retrieved properly", result, 506);
    }
    @Test
    public void setTitleTest() throws NoSuchFieldException, IllegalAccessException {
        String value = "Museo Ejemplo";
        Museo museo = new Museo();
        museo.setTitle(value);
        Field field = museo.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), value);
    }

    @Test
    public void getTitleTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Field field = museo.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(museo, "Museo Ejemplo");

        String result = museo.getTitle();
        assertEquals("Field wasn't retrieved properly", result, "Museo Ejemplo");
    }

    @Test
    public void setRelationTest() throws NoSuchFieldException, IllegalAccessException {
        String value = "relation ejemplo";
        Museo museo = new Museo();
        museo.setRelation(value);
        Field field = museo.getClass().getDeclaredField("relation");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), value);
    }

    @Test
    public void getRelationTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Field field = museo.getClass().getDeclaredField("relation");
        field.setAccessible(true);
        field.set(museo, "relation ejemplo");

        String result = museo.getRelation();
        assertEquals("Field wasn't retrieved properly", result, "relation ejemplo");
    }

    @Test
    public void setAddressTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address("locality","10001","streetAddress");
        Museo museo = new Museo();
        museo.setAddress(address);
        Field field = museo.getClass().getDeclaredField("address");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), address);
    }

    @Test
    public void getAddressTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Address address = new Address("locality","10001","streetAddress");
        Field field = museo.getClass().getDeclaredField("address");
        field.setAccessible(true);
        field.set(museo, address);

        Address result = museo.getAddress();
        assertEquals("Field wasn't retrieved properly", result, address);
    }

    @Test
    public void setLocationTest() throws NoSuchFieldException, IllegalAccessException {
        Location location = new Location(222.22f, 435.434f);
        Museo museo = new Museo();
        museo.setLocation(location);
        Field field = museo.getClass().getDeclaredField("location");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), location);
    }

    @Test
    public void getLocationTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Location location = new Location(222.22f, 435.434f);
        Field field = museo.getClass().getDeclaredField("location");
        field.setAccessible(true);
        field.set(museo, location);

        Location result = museo.getLocation();
        assertEquals("Field wasn't retrieved properly", result, location);
    }

    @Test
    public void setOrganizationTest() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization("organizationDesc", "accesibility", "schedule");
        Museo museo = new Museo();
        museo.setOrganization(organization);
        Field field = museo.getClass().getDeclaredField("organization");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(museo), organization);
    }

    @Test
    public void getOrganizationTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new Museo();
        Organization organization = new Organization("organizationDesc", "accesibility", "schedule");
        Field field = museo.getClass().getDeclaredField("organization");
        field.setAccessible(true);
        field.set(museo, organization);

        Organization result = museo.getOrganization();
        assertEquals("Field wasn't retrieved properly", result, organization);
    }

    @Test
    public void setFavTest() throws NoSuchFieldException, IllegalAccessException {
        int value = 1;
        Museo museo = new  Museo();
        museo.setFav(value);
        Field field = museo.getClass().getDeclaredField("fav");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(museo), value);
    }

    @Test
    public void getFavTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new  Museo();
        Field field = museo.getClass().getDeclaredField("fav");
        field.setAccessible(true);
        field.set(museo, 1);

        int result = museo.getFav();
        assertEquals(result,1);
    }

    @Test
    public void setDeseoTest() throws NoSuchFieldException, IllegalAccessException {
        int value = 1;
        Museo museo = new  Museo();
        museo.setDeseo(value);
        Field field = museo.getClass().getDeclaredField("deseo");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(museo), value);
    }

    @Test
    public void getDeseoTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new  Museo();
        Field field = museo.getClass().getDeclaredField("deseo");
        field.setAccessible(true);
        field.set(museo, 1);

        int result = museo.getDeseo();
        assertEquals(result,1);
    }

    @Test
    public void setRutaTest() throws NoSuchFieldException, IllegalAccessException {
        int value = 1;
        Museo museo = new  Museo();
        museo.setRuta(value);
        Field field = museo.getClass().getDeclaredField("ruta");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(museo), value);
    }

    @Test
    public void getRutaTest() throws NoSuchFieldException, IllegalAccessException {
        Museo museo = new  Museo();
        Field field = museo.getClass().getDeclaredField("ruta");
        field.setAccessible(true);
        field.set(museo, 1);

        int result = museo.getRuta();
        assertEquals(result,1);
    }

}
