package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import at.petshome.R;
import at.petshome.Miscellaneous.Settings;

public class CreateProfileActivity extends AppCompatActivity {
    private EditText mNameField;
    private EditText mCityField;
    private EditText mZIPField;

    private ArrayList<String> mList = null;
    private ArrayAdapter mAdapter = null;
    private Spinner mSpinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mNameField = findViewById(R.id.profile_name);
        mCityField = findViewById(R.id.profile_city);
        mZIPField = findViewById(R.id.profile_zip);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mSpinner = findViewById(R.id.profile_type);
        mList = new ArrayList<>();
        mList.add("Dog");
        mList.add("Cat");
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mList);
        mSpinner.setAdapter(mAdapter);
    }

    public void createProfile(View view) {
        boolean wrong = false;

        if (mNameField.getText() == null || mNameField.getText().toString().equals("")) {
            mNameField.setError("Wrong format");
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
        database.execSQL(String.format("INSERT INTO pets(user_id, name, type, city, zip) VALUES(%d, '%s', '%s', '%s', %d)", Settings.getInstance().getUid(), mNameField.getText().toString(), mList.get(mSpinner.getSelectedItemPosition()), mCityField.getText().toString(), zipCode));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}