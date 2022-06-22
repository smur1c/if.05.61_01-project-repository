package at.petshome;

public class Petkeeper {
    private int mId;
    private String mEmail;
    private String mName;
    private String mAbout;
    private String mPetType;
    private String mCity;
    private int mZip;

    public Petkeeper() {

    }

    public Petkeeper(int id, String email, String name, String about, String petType, String city, int zip) {
        mId = id;
        mEmail = email;
        mName = name;
        mAbout = about;
        mPetType = petType;
        mCity = city;
        mZip = zip;
    }

    public int getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String about) {
        mAbout = about;
    }

    public String getPetType() {
        return mPetType;
    }

    public void setPetType(String petType) {
        mPetType = petType;
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
