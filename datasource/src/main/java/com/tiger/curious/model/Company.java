package com.tiger.curious.model;

/**
 * Created by bkang016 on 5/29/17.
 */

public class Company {
    private String company_name;

    private String englishName;

    private String room;

    private int floor;

    private String abbreviation;

    private String group_name;


    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_name='" + company_name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", room='" + room + '\'' +
                ", floor=" + floor +
                ", abbreviation='" + abbreviation + '\'' +
                ", group_name='" + group_name + '\'' +
                '}';
    }
}
