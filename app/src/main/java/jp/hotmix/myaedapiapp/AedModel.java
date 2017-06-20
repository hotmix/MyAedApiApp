package jp.hotmix.myaedapiapp;

/**
 * Created by hotmix on 2017/06/14.
 */

public class AedModel{
    private long Id;
    private String LocationName;
    private String Perfecture;
    private String City;
    private String Latitude;
    private String Longitude;

    public long getId() {
        return Id;
    }

    public String getLocationName() {
        return LocationName;
    }

    public String getPerfecture() {
        return Perfecture;
    }

    public String getCity() {
        return City;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }
}
