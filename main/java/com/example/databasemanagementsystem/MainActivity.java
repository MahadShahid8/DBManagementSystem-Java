package com.example.databasemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String Key_id = "id";
    private static final String Key_name = "name";
    private static final String Key_loc = "location";
    private static final String Key_desig = "designation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etname = findViewById(R.id.et_name);
        EditText etloc = findViewById(R.id.et_loc);
        EditText etdesig = findViewById(R.id.et_desig);
        EditText etid = findViewById(R.id.et_id);
        TextView tvData = findViewById(R.id.tv);
        Button btninsert = findViewById(R.id.btn_insert);
        Button btndelete = findViewById(R.id.btn_delete);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString();
                String loc = etloc.getText().toString();
                String desig = etdesig.getText().toString();
                DBHandler datahandler = new DBHandler(MainActivity.this);
                datahandler.insertUser(name, loc, desig);
                ArrayList<HashMap<String, String>> userlist = datahandler.getUser();
                tvData.setText("");
                for (HashMap<String, String> user : userlist) {
                    String record = tvData.getText() + "name : " + user.get(Key_name) + "Location : " + user.get(Key_loc) + "Designation : " + user.get(Key_desig) + "\n";
                    tvData.setText(record);
                }


            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler datahandler = new DBHandler(MainActivity.this);
                datahandler.delete(Integer.parseInt(Key_id));
                ArrayList<HashMap<String, String>> userlist = datahandler.getUser();
                tvData.setText("");
                for (HashMap<String, String> user : userlist) {
                    String record = tvData.getText() + "name : " + user.get(Key_name) + "Location : " + user.get(Key_loc) + "Designation : " + user.get(Key_desig) + "\n";
                    tvData.setText(record);
                }
            }
        });
    }
}