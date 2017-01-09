package com.sandyz.contextmmenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int call = 100;
    private static final int sms = 101;
    private static final int Pick_Contact = 1;


    ListView listView;
    String[] name = new String[]{"Name 1", "Name 2", "Name 3", "Name 4", "Name 5", "Name 6", "Name 7", "Name 8", "Name 9", "Name 10"};
    String[] numb = new String[]{"PhoneNumber 1 ", "Phonenumber 2", "Phonenumber 3", "Phonenumber 4", "Phonenumber 5", "Phonenumber 6", "Phonenumber 7", "Phonenumber 8", "Phonenumber 9", "Phonenumber 10"};
    List<CustomGetSet> model = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.contacts);
        for (int i = 0; i < name.length; i++) {


            CustomGetSet handler = new CustomGetSet();
            handler.setName(name[i]);
            handler.setNumber(numb[i]);
            model.add(handler);
        }
        CustomAdapter listAdaper = new CustomAdapter(this, model);
        listView.setAdapter(listAdaper);
        //registering for contextmenu
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose an action");
        menu.add(0, call, 1, "call");
        menu.add(0, sms, 2, "Send sms");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId()== call && item.getGroupId()==0) {

            Intent intent = new Intent(Intent.ACTION_DIAL);



            startActivityForResult(intent, Pick_Contact);
        }

        if (item.getItemId()== sms && item.getGroupId()==0) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);



            startActivityForResult(intent, Pick_Contact);
        }

        return true;
    }
}

