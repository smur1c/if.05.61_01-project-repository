package at.petshome.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import at.petshome.Entities.Petkeeper;
import at.petshome.R;
import at.petshome.Miscellaneous.Settings;

public class EditPetkeeperProfileActivity extends AppCompatActivity {
    private ArrayList<String> mList = null;
    private ArrayAdapter mAdapter = null;
    private Spinner mSpinner = null;

    private EditText mEmailField = null;
    private EditText mNameField = null;
    private EditText mAboutField = null;
    private EditText mCityField = null;
    private EditText mZIPField = null;

    private Petkeeper mPetkeeper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_petkeeper_profile);

        mSpinner = findViewById(R.id.petkeeper_edit_type);
        mList = new ArrayList<>();
        mList.add("Dog");
        mList.add("Cat");
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mList);
        mSpinner.setAdapter(mAdapter);

        mEmailField = findViewById(R.id.petkeeper_edit_email);
        mNameField = findViewById(R.id.petkeeper_edit_full_name);
        mAboutField = findViewById(R.id.petkeeper_edit_aboutyou);
        mCityField = findViewById(R.id.petkeeper_edit_city);
        mZIPField = findViewById(R.id.petkeeper_edit_zip);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery(String.format("SELECT id, email, name, about, type, city, zip FROM petkeepers WHERE user_id = %d", Settings.getInstance().getUid()), null);

        if (cursor.getCount() == 0) {
            System.out.println("error");
            return;
        }

        while (cursor.moveToNext()) {
            mPetkeeper = new Petkeeper(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
        }

        mEmailField.setText(mPetkeeper.getEmail());
        mNameField.setText(mPetkeeper.getName());
        mAboutField.setText(mPetkeeper.getAbout());
        mCityField.setText(mPetkeeper.getCity());
        mZIPField.setText(String.valueOf(mPetkeeper.getZip()));
        mSpinner.setSelection(mList.indexOf(mPetkeeper.getPetType()));
    }

    public void saveProfile(View view) {
        String petType = mList.get(mSpinner.getSelectedItemPosition());

        boolean wrong = false;

        if (mEmailField.getText() == null || mEmailField.getText().toString().equals("")) {
            mEmailField.setError("Wrong format");
            wrong = true;
        }

        if (mNameField.getText() == null || mNameField.getText().toString().equals("")) {
            mNameField.setError("Wrong format");
            wrong = true;
        }

        if (mAboutField.getText() == null || mAboutField.getText().toString().equals("")) {
            mAboutField.setError("Wrong format");
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
        database.execSQL(String.format("UPDATE petkeepers SET email = '%s', name = '%s', about = '%s', type = '%s', city = '%s', zip = %d WHERE id = %d", mEmailField.getText().toString(), mNameField.getText().toString(), mAboutField.getText().toString(), petType, mCityField.getText().toString(), zipCode, mPetkeeper.getId()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}