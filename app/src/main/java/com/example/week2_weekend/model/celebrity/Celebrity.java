package com.example.week2_weekend.model.celebrity;

import android.os.Parcel;
import android.os.Parcelable;

public class Celebrity implements Parcelable {
    private String celebrityName;
    private String celebrityAge;
    private String celebrityGender;
    private String celebrityType;
    private String id;

    public Celebrity(){
    }

    public Celebrity(String celebrityName, String celebrityAge, String celebrityGender, String celebrityType, String id) {
        this.celebrityName = celebrityName;
        this.celebrityAge = celebrityAge;
        this.celebrityGender = celebrityGender;
        this.celebrityType = celebrityType;
        this.id = id;
    }

    protected Celebrity(Parcel in) {
        celebrityName = in.readString();
        celebrityAge = in.readString();
        celebrityGender = in.readString();
        celebrityType = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(celebrityName);
        parcel.writeString(celebrityAge);
        parcel.writeString(celebrityGender);
        parcel.writeString(celebrityType);
        parcel.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public String getCelebrityAge() {
        return celebrityAge;
    }

    public void setCelebrityAge(String celebrityAge) {
        this.celebrityAge = celebrityAge;
    }

    public String getCelebrityGender() {
        return celebrityGender;
    }

    public void setCelebrityGender(String celebrityGender) {
        this.celebrityGender = celebrityGender;
    }

    public String getCelebrityType() {
        return celebrityType;
    }

    public void setCelebrityType(String celebrityType) {
        this.celebrityType = celebrityType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
