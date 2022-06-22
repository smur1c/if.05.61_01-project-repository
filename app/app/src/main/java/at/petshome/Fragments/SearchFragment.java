package at.petshome.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

import at.petshome.Activities.EditProfileActivity;
import at.petshome.Activities.ShowPetkeeperActivity;
import at.petshome.Entities.Pet;
import at.petshome.Entities.Petkeeper;
import at.petshome.Miscellaneous.Settings;
import at.petshome.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private ArrayList<Petkeeper> mList = null;
    private ArrayList<Pet> mPetList = null;
    private ArrayAdapter mAdapter = null;
    private Spinner mSpinner = null;
    private ArrayAdapter mSpinnnerAdapter = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void handleFab() {
        if (mSpinner.getSelectedItemPosition() == -1) {
            Toast.makeText(getContext(), "Register a pet first in order to search", Toast.LENGTH_SHORT).show();
            return;
        }

        mAdapter.clear();
        mList.clear();
        File file = getContext().getDatabasePath("PetsHome");

        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(file, null);

        Cursor cursor = database.rawQuery(String.format("SELECT id, email, name, about, city, zip FROM petkeepers WHERE type = '%s' AND NOT user_id = %d", mPetList.get(mSpinner.getSelectedItemPosition()).getType(), Settings.getInstance().getUid()), null);

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No petkeepers found for search filter", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            mList.add(new Petkeeper(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), mPetList.get(mSpinner.getSelectedItemPosition()).getType(), cursor.getString(4), cursor.getInt(5)));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void handleItemClick(int position) {
        Petkeeper pk = mList.get(position);
        System.out.println(pk.getPetType());

        Intent intent = new Intent(getContext(), ShowPetkeeperActivity.class);
        intent.putExtra("email", pk.getEmail());
        intent.putExtra("name", pk.getName());
        intent.putExtra("about", pk.getAbout());
        intent.putExtra("pettype", pk.getPetType());
        intent.putExtra("city", pk.getCity());
        intent.putExtra("zip", pk.getZip());
        startActivity(intent);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.search_fab);
        ListView lv = view.findViewById(R.id.search_results);

        fab.setOnClickListener(v -> handleFab());
        mList = new ArrayList<>();
        mAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mList);

        mSpinner = view.findViewById(R.id.search_selection);
        mPetList = new ArrayList<>();

        mSpinnnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, mPetList);
        mSpinner.setAdapter(mSpinnnerAdapter);

        lv.setOnItemClickListener(((parent, view1, position, id) -> handleItemClick(position)));
        lv.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        File file = getContext().getDatabasePath("PetsHome");
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(file, null);

        Cursor cursor = database.rawQuery(String.format("SELECT id, name, type, city, zip FROM pets WHERE user_id = %d", Settings.getInstance().getUid()), null);

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No pets available for search", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            mPetList.add(new Pet(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
        }

        mSpinnnerAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}