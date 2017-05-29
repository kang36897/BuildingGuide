package com.tiger.curious.model;

/**
 * Created by bkang016 on 5/29/17.
 */

public class Company {

    private String name;

    private String englishName;

    private String roomNumber;

    private int floor;

    private String abbreviation;

    private String group;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", floor=" + floor +
                ", abbreviation='" + abbreviation + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}