package es.unex.goenjoy.converter;

import androidx.room.TypeConverter;

import es.unex.goenjoy.model.Address;

public class AddressConverter {
    @TypeConverter
    public static String AddressToStreetAddress(Address address){
        return address == null ? null : address.getStreetAddress();
    }
     @TypeConverter
    public static Address StreetAddressToAddress(String streetAddress){
        return streetAddress == null ? null : new Address(streetAddress);
     }
}
