package es.unex.goenjoy.converter;

import androidx.room.TypeConverter;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Location;

public class LocationConverter {
    @TypeConverter
    public static String LocationToLatitude(Location location){
        String aux = location.getLatitude() + "/" + location.getLongitude() ;
        return location == null ? null : aux;
    }
    @TypeConverter
    public static Location LatitudeToLocation(String list){
        String[] listAux = list.split("/");
        Float latitude = Float.parseFloat(listAux[0]);
        Float longitude = Float.parseFloat(listAux[1]);
        return list == null ? null : new Location(latitude,longitude);
    }
}
