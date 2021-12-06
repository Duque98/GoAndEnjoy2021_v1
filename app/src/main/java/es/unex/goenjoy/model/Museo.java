package es.unex.goenjoy.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import es.unex.goenjoy.converter.AddressConverter;
import es.unex.goenjoy.converter.LocationConverter;
import es.unex.goenjoy.converter.OrganizationConverter;

/*
Clase Museo para gestionar la entidad museo
 */
@Entity(tableName = "museos")
public class Museo {
    @NonNull
    @SerializedName("id")
    @Expose
    @PrimaryKey()
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("address")
    @Expose
    @TypeConverters({AddressConverter.class})
    private Address address;
    @SerializedName("location")
    @Expose
    @TypeConverters({LocationConverter.class})
    private Location location;
    @SerializedName("organization")
    @Expose
    @TypeConverters({OrganizationConverter.class})
    private Organization organization;
    private int fav;
    private int deseo;
    private int ruta;

    @Ignore
    public Museo(){
        this.id = 0;
        this.title = "";
        this.relation = "";
        this.address = null;
        this.location = null;
        this.organization = null;
        this.fav = 0;
        this.deseo=0;
        this.ruta = 0;
    }


    public Museo(int id, String title, String relation, Address address, Location location, Organization organization, int fav, int deseo, int ruta) {
        this.id = id;
        this.title = title;
        this.relation = relation;
        this.address = address;
        this.location = location;
        this.organization = organization;
        this.fav = fav;
        this.deseo = deseo;
        this.ruta = ruta;
    }



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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
