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
    private EditText mAddressField;
    private Pet mPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    protected void onStart() {
        mNameField = findViewById(R.id.edit_name);
        mTypeField = findViewById(R.id.edit_type);
        mAddressField = findViewById(R.id.edit_address);

        Bundle bundle = getIntent().getExtras();

        mPet = new Pet(bundle.getInt("id"), bundle.getString("name"), bundle.getString("type"), bundle.getString("address"));
        mNameField.setText(mPet.getName());
        mTypeField.setText(mPet.getType());
        mAddressField.setText(mPet.getAddress());

        super.onStart();
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

        if (mAddressField.getText() == null || mAddressField.getText().toString().equals("")) {
            mTypeField.setError("Wrong format (City-ZIP)");
            wrong = true;
        }

        if (wrong) {
            return;
        }

        mPet.setName(mNameField.getText().toString());
        mPet.setType(mTypeField.getText().toString());
        mPet.setAddress(mAddressField.getText().toString());

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        database.execSQL(String.format("UPDATE pets SET name = '%s', type = '%s', address = '%s' WHERE id = %d", mPet.getName(), mPet.getType(), mPet.getAddress(), mPet.getId()));
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