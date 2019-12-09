package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //Shared Preferences
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons
        Button save = findViewById(R.id.button);
        Button retrieve = findViewById(R.id.button2);
        Button clear = findViewById(R.id.button3);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear(v);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save(v);
                closeKeyboard();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrieve(v);
            }
        });

    }

    public void Clear(View view) {
        TextView name = findViewById(R.id.etName);
        TextView email = findViewById(R.id.etEmail);
        TextView password = findViewById(R.id.etPass);
        name.setText("");
        email.setText("");
        password.setText("");
    }

    public void Save(View view) {
        //text fields
        EditText name = findViewById(R.id.etName);
        EditText email = findViewById(R.id.etEmail);
        EditText pass = findViewById(R.id.etPass);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, name.getText().toString());
        editor.putString(Email, email.getText().toString());
        editor.putString(Password, pass.getText().toString());
        editor.commit();
    }

    public void Retrieve(View view) {
        //text fields
        EditText name = findViewById(R.id.etName);
        EditText email = findViewById(R.id.etEmail);
        EditText pass = findViewById(R.id.etPass);

        name.setText(sharedpreferences.getString("nameKey", "DEFAULT"));
        email.setText(sharedpreferences.getString("emailKey", "DEFAULT"));
        pass.setText(sharedpreferences.getString("passwordKey", "DEFAULT"));
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
