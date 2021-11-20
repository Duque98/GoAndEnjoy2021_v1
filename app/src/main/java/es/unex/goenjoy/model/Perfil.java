package es.unex.goenjoy.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


/**
 * Clase necesaria para almacenar la información respectiva a un usuario.
 */
@Entity(tableName = "perfil")
public class Perfil {
    @NotNull
    @PrimaryKey()
    private int id;
    private String nombre;
    private String email;

    @Ignore
    public Perfil(){
        this.id = 1;
        this.nombre = "";
        this.email = "";
    }

    public Perfil(int id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    //Metodos set
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Metodos get
    public String getNombre(){
        return this.nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public int getId() {
        return id;
    }
}


