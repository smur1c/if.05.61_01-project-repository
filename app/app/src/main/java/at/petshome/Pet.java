package at.petshome;

public class Pet {
    private int mId;
    private String mName;
    private String mType;
    private String mCity;
    private int mZIP;

    public Pet() {

    }

    public Pet(int id, String name, String type, String city, int ZIP) {
        mId = id;
        mName = name;
        mType = type;
        mCity = city;
        mZIP = ZIP;
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

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public int getZIP() {
        return mZIP;
    }

    public void setZIP(int ZIP) {
        mZIP = ZIP;
    }

    @Override
    public String toString() {
        return mName;
    }
}
