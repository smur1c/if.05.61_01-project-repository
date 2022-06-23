package at.petshome.Entities;

public class Petkeeper extends Entity {
    private String mEmail;
    private String mAbout;
    private String mPetType;

    public Petkeeper() {

    }

    public Petkeeper(int id, String email, String name, String about, String petType, String city, int zip) {
        super(id, name, city, zip);
        mEmail = email;
        mAbout = about;
        mPetType = petType;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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

    @Override
    public String toString() {
        return super.getName() + " | " + getEmail();
    }
}
