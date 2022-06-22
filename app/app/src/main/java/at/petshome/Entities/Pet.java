package at.petshome.Entities;

public class Pet extends Entity {
    private String mType;

    public Pet() {

    }

    public Pet(int id, String name, String type, String city, int zip) {
        super(id, name, city, zip);
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
