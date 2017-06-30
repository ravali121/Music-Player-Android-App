package com.ravali121.musicplaylist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomePageActivity extends AppCompatActivity {

    final Context context = this;
    private Button btn;
    private EditText editTextDialogUserInput;
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn = (Button)findViewById(R.id.btn);
        listView = (ListView)findViewById(R.id.plist);

        String[] playlists = {"Favorites","Jazz","Melody"};
        arrayList=new ArrayList<>(Arrays.asList(playlists));
        adapter=new ArrayAdapter<String>(this, R.layout.list_item,R.id.txtView,arrayList);
        listView.setAdapter(adapter);

        //Add onClick Listener on the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptView = li.inflate(R.layout.userinputprompt,null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptView);

                editTextDialogUserInput = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Create",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                 String newPItem = editTextDialogUserInput.getText().toString();
                                arrayList.add(newPItem);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }
}
