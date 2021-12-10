package es.unex.goenjoy.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Field;

import es.unex.goenjoy.model.Perfil;

public class PerfilUnitTest {

    @Test
    public void constructorTest(){
        Perfil perfil = new Perfil(706, "Pablo Lucas", "pablo@gmail.com");
        assertEquals(perfil.getId(), 706);
        assertEquals(perfil.getNombre(), "Pablo Lucas");
        assertEquals(perfil.getEmail(), "pablo@gmail.com");
    }

    @Test
    public void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        int value =506;
        Perfil perfil = new Perfil();
        perfil.setId(value);
        Field field = perfil.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Field didn't match", field.get(perfil), value);
    }
    @Test
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        Perfil perfil = new Perfil();
        Field field = perfil.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(perfil, 12);

        int result = perfil.getId();
        assertEquals("Field wasn't retrieved properly", result, 12);
    }


    @Test
    public void setNombreTest() throws  NoSuchFieldException, IllegalAccessException {
        String value = "Juan Pedro Lopez";
        Perfil perfil = new Perfil();
        perfil.setNombre(value);
        Field field = perfil.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(perfil), value);
    }

    @Test
    public void getNombreTest() throws NoSuchFieldException, IllegalAccessException {
        Perfil perfil = new Perfil();
        Field field = perfil.getClass().getDeclaredField("nombre");
        field.setAccessible(true);
        field.set(perfil, "Federico Pizarro");

        String result = perfil.getNombre();
        assertEquals("Field wasn't retrieved properly", result, "Federico Pizarro");
    }

    @Test
    public void setEmailTest() throws NoSuchFieldException, IllegalAccessException {
        String value = "federicopizarrin@gmail.com";
        Perfil perfil = new Perfil();
        perfil.setEmail(value);
        Field field = perfil.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(perfil), value);
    }

    @Test
    public void getEmailTest() throws NoSuchFieldException, IllegalAccessException {
        Perfil perfil = new Perfil();
        Field field = perfil.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(perfil, "federicopizarrin@gmail.com");

        final String result = perfil.getEmail();
        assertEquals("Field wasn't retrieved properly", result, "federicopizarrin@gmail.com");
    }
}
