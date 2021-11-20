package es.unex.goenjoy.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/*
Clase Museo para gestionar la entidad museo
 */
@Entity(tableName = "museos")
public class Museo {
    @NonNull
    @PrimaryKey()
    private int id;
    private String title;
    private String relation;
    private String localidad;
    private String postalCode;
    private String streetAdress;
    private Float latitude;
    private Float longitude;
    private String desc;
    private String accesibility;
    private String schedule;
    private int fav;
    private int deseo;
    private int ruta;
    private int tipo; //0-default 1-museo 2-parque

    @Ignore
    public Museo(){
        this.id = 0;
        this.title = "";
        this.relation = "";
        this.localidad = "";
        this.postalCode = "";
        this.streetAdress = "";
        this.latitude = 0.0f;
        this.longitude = 0.0f;
        this.desc = "";
        this.accesibility = "";
        this.schedule = "";
        this.fav = 0;
        this.deseo=0;
        this.ruta = 0;
        this.tipo = 0;
    }


    public Museo(int id, String title, String relation, String localidad, String postalCode, String streetAdress, Float latitude, Float longitude, String desc, String accesibility, String schedule, int fav, int deseo, int ruta, int tipo) {
        this.id = id;
        this.title = title;
        this.relation = relation;
        this.localidad = localidad;
        this.postalCode = postalCode;
        this.streetAdress = streetAdress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.desc = desc;
        this.accesibility = accesibility;
        this.schedule = schedule;
        this.fav = fav;
        this.deseo = deseo;
        this.ruta = ruta;
        this.tipo = tipo;
    }

    public int getTipo() {return tipo;}

    public void setTipo(int tipo) {this.tipo = tipo;}

    public int getRuta() {return ruta;}

    public void setRuta(int ruta) {this.ruta = ruta;}

    public int getFav() { return fav; }

    public void setFav(int fav) { this.fav = fav; }

    public int getDeseo() {return deseo;}

    public void setDeseo(int deseo) {this.deseo = deseo;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
