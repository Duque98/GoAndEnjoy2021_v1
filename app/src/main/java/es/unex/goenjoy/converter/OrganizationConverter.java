package es.unex.goenjoy.converter;

import androidx.room.TypeConverter;

import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Organization;

public class OrganizationConverter {
    @TypeConverter
    public static String OrganizationToSchedule(Organization organization){
        String aux = "";
        if(organization.getOrganizationDesc().isEmpty()){
            aux += " "+"@";
        }else{
            aux+= organization.getOrganizationDesc()+"@";
        }
        if(organization.getAccesibility().isEmpty()){
            aux+= " "+"@";
        }else{
            aux+= organization.getAccesibility()+"@";
        }
        if(organization.getSchedule().isEmpty()){
            aux+=" "+"@";
        }else{
            aux+= organization.getSchedule()+"@";
        }
        return organization == null ? null : aux;
    }
    @TypeConverter
    public static Organization ScheduleToOrganization(String list){
        String[] listAux = list.split("@");
        String desc = " ";
        if(!listAux[0].isEmpty()){
            desc = listAux[0];
        }
        String accesibility = " ";
        if(!listAux[1].isEmpty()){
            accesibility = listAux[1];
        }
        String schedule = " ";
        if(!listAux[2].isEmpty()){
            schedule = listAux[2];
        }
        return list == null ? null : new Organization(desc,accesibility,schedule);
    }
}
