package com.example.rahul.zylaassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rahul.zylaassignment.adapter.ParentAdapter;
import com.example.rahul.zylaassignment.Model.Child;
import com.example.rahul.zylaassignment.Model.ParentChild;
import com.example.rahul.zylaassignment.Model.SongDataModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    ArrayList<ParentChild> parentChildObj;
    Toolbar myToolbar;
    Spinner rowSpinner, collectSpinner;
    private RecyclerView recyclerView;
    public static int columnNum =1;
    ParentAdapter parentAdapter;
    HashMap<String, ArrayList<Child>> mapArtist = new HashMap<>();
    HashMap<String, ArrayList<Child>> mapAlbum = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentChildObj = new ArrayList<>();

        //Reading Data from sample_music_data.csv
        readData();

        myToolbar =  findViewById(R.id.toolbar);
        rowSpinner = findViewById(R.id.spinner);
        collectSpinner = findViewById(R.id.cat_spinner);
        recyclerView = findViewById(R.id.recycle_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        parentAdapter = new ParentAdapter(this, parentChildObj);
        recyclerView.setAdapter(parentAdapter);
        
        //Spinner for Changing no. of column in grid
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
               R.layout.custom_spinner_item,
               getResources().getStringArray(R.array.showSize));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rowSpinner.setAdapter(myAdapter);

        rowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              
                switch (rowSpinner.getSelectedItem().toString() ) {
                    case "1":
                             columnNum = 1;
                             parentAdapter.notifyDataSetChanged();
                             break;

                    case "2":
                             columnNum = 2;
                             parentAdapter.notifyDataSetChanged();
                             break;
                    case "3":
                             columnNum = 3;
                             parentAdapter.notifyDataSetChanged();
                             break;
                    case "4":
                             columnNum = 4;
                             parentAdapter.notifyDataSetChanged();
                             break;
                    case "5":
                             columnNum = 5;
                             parentAdapter.notifyDataSetChanged();
                             break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for changing collection type based on Artist or Album
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.custom_spinner_item,
                getResources().getStringArray(R.array.filterWith));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collectSpinner.setAdapter(mAdapter);

        collectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch ( collectSpinner.getSelectedItem().toString()) {
                    case "Artist":
                                  parentChildObj.clear();
                                  createData(collectSpinner.getSelectedItem().toString());
                                  parentAdapter.notifyDataSetChanged();
                                  break;


                    case "Album":
                                  parentChildObj.clear();
                                  createData(collectSpinner.getSelectedItem().toString());
                                  parentAdapter.notifyDataSetChanged();
                                  break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //method call from spinner
    private ArrayList<ParentChild> createData(String cat) {

        switch ( cat) {
            case "Artist":
                           for ( String key : mapArtist.keySet() ) {
                                ParentChild pc1 = new ParentChild();
                                pc1.setChild(mapArtist.get(key));
                                pc1.setCategoryName(key);
                                parentChildObj.add(pc1);
                            }
                            break;
            case "Album":
                            for ( String key : mapAlbum.keySet() ) {
                                ParentChild pc1 = new ParentChild();
                                pc1.setChild(mapAlbum.get(key));
                                pc1.setCategoryName(key);
                                parentChildObj.add(pc1);
                            }
                             break;
        }
        return parentChildObj;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    //Reading and storing data in data structure in ArrayList
    private ArrayList<SongDataModel> songDataModelList = new ArrayList<>();
    private ArrayList<SongDataModel> readData() {
        InputStream is = getResources().openRawResource(R.raw.sample_music_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line ;

        try{
            reader.readLine();

            while(( line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                SongDataModel song = new SongDataModel();
                Child child = new Child();
                child.setSong_name(tokens[0]);
                addToMapArtist(mapArtist, tokens[1], child);
                addToMapAlbum(mapAlbum, tokens[2], child);
                song.setName(tokens[0]);
                song.setArtist(tokens[1]);
                song.setAlbum(tokens[2]);
                songDataModelList.add(song);

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return songDataModelList;
    }

    //mapping artist with songs
    public void addToMapArtist(HashMap<String, ArrayList<Child> >map1, String key, Child value){
        if(!map1.containsKey(key)){
            map1.put(key, new ArrayList<Child>());
        }
        map1.get(key).add(value);
    }

    //mapping album with songs
    public void addToMapAlbum(HashMap<String, ArrayList<Child> >map2, String key, Child value){
        if(!map2.containsKey(key)){
            map2.put(key, new ArrayList<Child>());
        }
        map2.get(key).add(value);
    }
}