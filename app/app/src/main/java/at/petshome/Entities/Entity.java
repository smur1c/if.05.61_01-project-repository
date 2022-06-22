package at.petshome.Entities;

public abstract class Entity {
    private int mId;
    private String mName;
    private String mCity;
    private int mZip;

    public Entity() {
    }

    public Entity(int id, String name, String city, int zip) {
        mId = id;
        mName = name;
        mCity = city;
        mZip = zip;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public int getZip() {
        return mZip;
    }

    public void setZip(int zip) {
        mZip = zip;
    }
}
