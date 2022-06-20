package at.petshome;

public class Pet {
    private int mId;
    private String mName;
    private String mType;
    private String mAddress;

    public Pet(int id, String name, String type, String address) {
        mId = id;
        mName = name;
        mType = type;
        mAddress = address;
    }

    public Pet() {

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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @Override
    public String toString() {
        return mName;
    }
}
