package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import at.petshome.R;
import at.petshome.Settings;

public class CreateProfileActivity extends AppCompatActivity {
    private EditText mNameField;
    private EditText mTypeField;
    private EditText mCityField;
    private EditText mZIPField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }

    @Override
    protected void onStart() {
        mNameField = findViewById(R.id.profile_name);
        mTypeField = findViewById(R.id.profile_type);
        mCityField = findViewById(R.id.profile_city);
        mZIPField = findViewById(R.id.profile_city);
        super.onStart();
    }

    public void createProfile(View view) {
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

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        database.execSQL(String.format("INSERT INTO pets(user_id, name, type, city, zip) VALUES(%d, '%s', '%s', '%s', %d)", Settings.getInstance().getUid(), mNameField.getText().toString(), mTypeField.getText().toString(), mCityField.getText().toString(), zipCode));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}