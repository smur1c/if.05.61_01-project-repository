package at.petshome;

public class Settings {
    private static Settings mSingleton = null;
    private int mUid;
    private String mEmail;
    private Pet mPet;

    private Settings() {
        mPet = new Pet();
    }

    public static Settings getInstance() {
        if (mSingleton == null) {
            mSingleton = new Settings();
        }

        return mSingleton;
    }

    public int getUid() {
        return mUid;
    }

    public void setUid(int mUid) {
        this.mUid = mUid;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public Pet getPet() {
        return mPet;
    }

    public void setPet(Pet pet) {
        mPet = pet;
    }
}
