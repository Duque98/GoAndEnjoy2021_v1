package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Area;
import es.unex.goenjoy.model.District;
import es.unex.goenjoy.model.Location;
import es.unex.goenjoy.model.Museo;

public class AddressUnitTest {
    @Test
    public void setLocalityTest() throws NoSuchFieldException, IllegalAccessException {
        String locality = "locality";
        Address address = new Address();
        address.setLocality(locality);
        Field field = address.getClass().getDeclaredField("locality");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(address), locality);
    }

    @Test
    public void getLocalityTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address();
        String locality = "locality";
        Field field = address.getClass().getDeclaredField("locality");
        field.setAccessible(true);
        field.set(address, locality);

        String result = address.getLocality();
        assertEquals("Field wasn't retrieved properly", result, locality);
    }

    @Test
    public void setPostalCodeTest() throws NoSuchFieldException, IllegalAccessException {
        String postalCode = "postalCode";
        Address address = new Address();
        address.setPostalCode(postalCode);
        Field field = address.getClass().getDeclaredField("postalCode");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(address), postalCode);
    }

    @Test
    public void getPostalCodeTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address();
        String postalCode = "postalCode";
        Field field = address.getClass().getDeclaredField("postalCode");
        field.setAccessible(true);
        field.set(address, postalCode);

        String result = address.getPostalCode();
        assertEquals("Field wasn't retrieved properly", result, postalCode);
    }

    @Test
    public void setStreetAddressTest() throws NoSuchFieldException, IllegalAccessException {
        String streetAddress = "streetAddress";
        Address address = new Address();
        address.setStreetAddress(streetAddress);
        Field field = address.getClass().getDeclaredField("streetAddress");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(address), streetAddress);
    }

    @Test
    public void getStreetAddressTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address();
        String streetAddress = "streetAddress";
        Field field = address.getClass().getDeclaredField("streetAddress");
        field.setAccessible(true);
        field.set(address, streetAddress);

        String result = address.getStreetAddress();
        assertEquals("Field wasn't retrieved properly", result, streetAddress);
    }

    @Test
    public void setDistrictTest() throws NoSuchFieldException, IllegalAccessException {
        District district = new District();
        Address address = new Address();
        address.setDistrict(district);
        Field field = address.getClass().getDeclaredField("district");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(address), district);
    }

    @Test
    public void getDistrictTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address();
        District district = new District();
        Field field = address.getClass().getDeclaredField("district");
        field.setAccessible(true);
        field.set(address, district);

        District result = address.getDistrict();
        assertEquals("Field wasn't retrieved properly", result, district);
    }

    @Test
    public void setAreaTest() throws NoSuchFieldException, IllegalAccessException {
        Area area = new Area();
        Address address = new Address();
        address.setArea(area);
        Field field = address.getClass().getDeclaredField("area");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(address), area);
    }

    @Test
    public void getAreaTest() throws NoSuchFieldException, IllegalAccessException {
        Address address = new Address();
        Area area = new Area();
        Field field = address.getClass().getDeclaredField("area");
        field.setAccessible(true);
        field.set(address, area);

        Area result = address.getArea();
        assertEquals("Field wasn't retrieved properly", result, area);
    }

}
