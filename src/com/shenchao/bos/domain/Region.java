package com.shenchao.bos.domain;

import java.util.Collection;

/**
 * Created by shenchao on 2016/11/24.
 */
public class Region {
    private String id;
    private String province;
    private String city;
    private String district;
    private String postcode;
    private String shortcode;
    private String citycode;
    private Collection<Subarea> subareas;

    public String getName(){
        return province+city+district;
    }

    public Region() {

    }

    /**
     * full constructor
     */
    public Region(String id, String province, String city, String district,
                  String postcode, String shortcode, String citycode, Collection<Subarea> subareas) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.postcode = postcode;
        this.shortcode = shortcode;
        this.citycode = citycode;
        this.subareas = subareas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (id != null ? !id.equals(region.id) : region.id != null) return false;
        if (province != null ? !province.equals(region.province) : region.province != null) return false;
        if (city != null ? !city.equals(region.city) : region.city != null) return false;
        if (district != null ? !district.equals(region.district) : region.district != null) return false;
        if (postcode != null ? !postcode.equals(region.postcode) : region.postcode != null) return false;
        if (shortcode != null ? !shortcode.equals(region.shortcode) : region.shortcode != null) return false;
        if (citycode != null ? !citycode.equals(region.citycode) : region.citycode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (shortcode != null ? shortcode.hashCode() : 0);
        result = 31 * result + (citycode != null ? citycode.hashCode() : 0);
        return result;
    }

    public Collection<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Collection<Subarea> subareas) {
        this.subareas = subareas;
    }
}
