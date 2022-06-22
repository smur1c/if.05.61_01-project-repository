package at.petshome.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import at.petshome.Entities.Pet;
import at.petshome.Entities.Petkeeper;
import at.petshome.Miscellaneous.Settings;
import at.petshome.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        NavController controller = Navigation.findNavController(this, R.id.fragment);

        NavigationUI.setupWithNavController(navigationView, controller);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_profile:
                Intent intent = new Intent(this, CreateProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.new_petkeeper_profile:

                SQLiteDatabase database = openOrCreateDatabase("PetsHome", MODE_PRIVATE, null);
                Cursor cursor = database.rawQuery(String.format("SELECT * FROM petkeepers WHERE user_id = %d", Settings.getInstance().getUid()), null);

                if (cursor.getCount() != 0) {
                    Toast.makeText(this, "User already has a petkeeper profile", Toast.LENGTH_SHORT).show();
                    break;
                }
                intent = new Intent(this, CreatePetkeeperProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.edit_petkeeper_profile:
                intent = new Intent(this, EditPetkeeperProfileActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
