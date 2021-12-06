package es.unex.goenjoy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {
    @SerializedName("organization-desc")
    @Expose
    private String organizationDesc;
    @SerializedName("accesibility")
    @Expose
    private String accesibility;
    @SerializedName("schedule")
    @Expose
    private String schedule;

    public Organization(String organizationDesc, String accesibility, String schedule){
        this.organizationDesc = organizationDesc;
        this.accesibility = accesibility;
        this.schedule = schedule;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
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
