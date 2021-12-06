package es.unex.goenjoy.converter;

import android.util.Log;

import androidx.room.TypeConverter;

import es.unex.goenjoy.model.Address;

public class AddressConverter {
    @TypeConverter
    public static String AddressToStreetAddress(Address address){
        String aux = address.getLocality() + "-" + address.getPostalCode() + "-" + address.getStreetAddress();
        return address == null ? null : aux;
    }
    @TypeConverter
    public static Address StreetAddressToAddress(String list){
        String[] listAux = list.split("-");
        String locality = listAux[0];
        String postalCode = listAux[1];
        String streetAddress = listAux[2];
        return list == null ? null : new Address(locality,postalCode,streetAddress);
     }

}
