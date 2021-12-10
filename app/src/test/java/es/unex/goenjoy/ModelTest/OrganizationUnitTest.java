package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Organization;

public class OrganizationUnitTest {

    @Test
    public void setOrganizationDescTest() throws NoSuchFieldException, IllegalAccessException {
        String organizationDesc = "organizationDesc";
        Organization organization = new Organization();
        organization.setOrganizationDesc(organizationDesc);
        Field field = organization.getClass().getDeclaredField("organizationDesc");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(organization), organizationDesc);
    }

    @Test
    public void getOrganizationDescTest() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization();
        String organizationDesc = "organizationDesc";
        Field field = organization.getClass().getDeclaredField("organizationDesc");
        field.setAccessible(true);
        field.set(organization, organizationDesc);

        String result = organization.getOrganizationDesc();
        assertEquals("Field wasn't retrieved properly", result, organizationDesc);
    }

    @Test
    public void setAccesibilityTest() throws NoSuchFieldException, IllegalAccessException {
        String accesibility = "accesibility";
        Organization organization = new Organization();
        organization.setAccesibility(accesibility);
        Field field = organization.getClass().getDeclaredField("accesibility");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(organization), accesibility);
    }

    @Test
    public void getAccesibilityTest() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization();
        String accesibility = "accesibility";
        Field field = organization.getClass().getDeclaredField("accesibility");
        field.setAccessible(true);
        field.set(organization, accesibility);

        String result = organization.getAccesibility();
        assertEquals("Field wasn't retrieved properly", result, accesibility);
    }

    @Test
    public void setScheduleTest() throws NoSuchFieldException, IllegalAccessException {
        String schedule = "schedule";
        Organization organization = new Organization();
        organization.setSchedule(schedule);
        Field field = organization.getClass().getDeclaredField("schedule");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(organization), schedule);
    }

    @Test
    public void getScheduleTest() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization();
        String schedule = "schedule";
        Field field = organization.getClass().getDeclaredField("schedule");
        field.setAccessible(true);
        field.set(organization, schedule);

        String result = organization.getSchedule();
        assertEquals("Field wasn't retrieved properly", result, schedule);
    }
}
