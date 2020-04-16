package com.example.tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private HashMap<String, String> items = new HashMap<>();
    private ArrayAdapter<String> adapter;
    public static final String DETAILS_EXTRA = "DETAILS_EXTRA";
    public static final int PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkUI();
        fetchShoppingItems();
        setAdapters();
        setClickListeners();
        requestPremissionCall();
//        if(savedInstanceState != null){
//            items = (HashMap<String,String>)savedInstanceState.getSerializable("Items");
//        }

    }

    @Override
    protected void onResume() {
        fetchShoppingItems();
        super.onResume();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    private void requestPremissionCall(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "You Selected Search option", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(myIntent);
                Toast.makeText(getApplicationContext(), "You Selected Settings option", Toast.LENGTH_LONG).show();
                break;
            case R.id.detail:
                Toast.makeText(getApplicationContext(),"You Selected Detail option",Toast.LENGTH_LONG).show();
                showAlertDialogButtonClicked();
                break;
            default:
                System.out.println("No selected");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickListeners() {
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) myListView.getItemAtPosition(position);

                String itemDescription = items.get(itemValue);

                Toast.makeText(getApplicationContext(), itemDescription, Toast.LENGTH_LONG).show();
                Intent detailActivityIntent = new Intent(MainActivity.this, DetailsActivity.class);
                detailActivityIntent.putExtra(DETAILS_EXTRA,itemDescription);
                MainActivity.this.startActivity(detailActivityIntent);
                Toast.makeText(getApplicationContext(), "You Selected Settings option", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void showAlertDialogButtonClicked() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notice");
        builder.setMessage("Daca aveti probleme contacteaza");
        // add the buttons
        builder.setPositiveButton("Call Service", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:07555555554"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }

        });
        builder.setNegativeButton("exit", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setAdapters() {
        List<String> values = new ArrayList<>(items.keySet());
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        myListView.setAdapter(adapter);

    }

    private void linkUI() {
        myListView = findViewById(R.id.listView);
    }

    private void fetchShoppingItems() {


        items.put("Castraveti", "importati din SPANIA");
        items.put("Morcovi", "Sunt buni pentru vedere si iepuri");
        items.put("Rosii", "au culoarea rosie");
        items.put("Ceapa", "Te face sa plangi");
        items.put("Ardei", "Pot fi capia sau iuti");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable("ShoppingList", items);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        items = (HashMap<String, String>) savedInstanceState.getSerializable("ShoppingList");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
