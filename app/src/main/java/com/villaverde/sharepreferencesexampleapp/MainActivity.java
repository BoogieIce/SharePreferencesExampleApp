package com.villaverde.sharepreferencesexampleapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText ageInput;
    private Button saveButton;
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullnameInput = findViewById(R.id.fullname_input);
        ageInput = findViewById(R.id.age_input);
        saveButton = findViewById(R.id.save_button);
        messageText = findViewById(R.id.message_input);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        String fullname = fullnameInput.getText().toString();
        String ageString = ageInput.getText().toString();

        if(fullname.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(this, "Ingrese los valores", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer age = Integer.parseInt(ageString);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean commited = sp.edit()
                .putString("fullname", fullname)
                .putInt("age", age)
                .putBoolean("storaged", true)
                .commit();

//        SharedPreferences sp2 = getSharedPreferences("sp2", MODE_PRIVATE);
//        SharedPreferences sp3 = getSharedPreferences("sp3", MODE_PRIVATE);

        Toast.makeText(this, "InformaciÃ³n almacenada", Toast.LENGTH_SHORT).show();

    }

    private void load(){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences( this);
                boolean storaged = sp.getBoolean("storaged", false);
        if(storaged){
            String fullname = sp.getString( "fullname", "");
            Integer age = sp.getInt("age", 0);
            messageText.setText("Nombres: " + fullname + "\nEdad: " + age);

        }

    }
}

