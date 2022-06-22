package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import at.petshome.Pet;
import at.petshome.R;

public class EditProfileActivity extends AppCompatActivity {
    private EditText mNameField;
    private EditText mTypeField;
    private EditText mCityField;
    private EditText mZIPField;
    private Pet mPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mNameField = findViewById(R.id.edit_name);
        mTypeField = findViewById(R.id.edit_type);
        mCityField = findViewById(R.id.edit_city);
        mZIPField = findViewById(R.id.edit_zip);

        Bundle bundle = getIntent().getExtras();

        mPet = new Pet(bundle.getInt("id"), bundle.getString("name"), bundle.getString("type"), bundle.getString("city"), bundle.getInt("zip"));
        mNameField.setText(mPet.getName());
        mTypeField.setText(mPet.getType());
        mCityField.setText(mPet.getCity());
        mZIPField.setText(mPet.getZIP());
    }

    public void editProfile(View view) {
        boolean wrong = false;

        if (mNameField.getText() == null || mNameField.getText().toString().equals("")) {
            mNameField.setError("Wrong format");
            wrong = true;
        }

        if (mTypeField.getText() == null || mTypeField.getText().toString().equals("")) {
            mTypeField.setError("Wrong format");
            wrong = true;
        }

        if (mCityField.getText() == null || mCityField.getText().toString().equals("")) {
            mCityField.setError("Wrong format");
            wrong = true;
        }

        if (mZIPField.getText() == null || mZIPField.getText().toString().equals("")) {
            mZIPField.setError("Wrong format");
            wrong = true;
        }

        int zipCode = 0;

        try {
            zipCode = Integer.parseInt(mZIPField.getText().toString());
        }
        catch (NumberFormatException ex) {
            wrong = true;
        }

        if (wrong) {
            return;
        }

        mPet.setName(mNameField.getText().toString());
        mPet.setType(mTypeField.getText().toString());
        mPet.setCity(mCityField.getText().toString());
        mPet.setZIP(zipCode);

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        database.execSQL(String.format("UPDATE pets SET name = '%s', type = '%s', city = '%s', zip = %d WHERE id = %d", mPet.getName(), mPet.getType(), mPet.getCity(), mPet.getZIP(), mPet.getId()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteProfile(View view) {
        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        database.execSQL(String.format("DELETE FROM pets WHERE id = %d", mPet.getId()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}