package com.example.androidlesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, view, sms;
    Button btnSend;
    boolean noteEmpty = false;

    protected String editEmail;
    protected String editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        view = findViewById(R.id.view);
        sms = findViewById(R.id.massage);
        btnSend = findViewById(R.id.send);
        editEmail = email.getText().toString();
        editText = view.getText().toString();


        email.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        btnSend.setBackgroundColor(Color.BLUE);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty()) {
                            btnSend.setBackgroundColor(Color.GRAY);
                            noteEmpty = false;
                        } else {
                            noteEmpty = true;
                            btnSend.setEnabled(true);
                        }

                    }

                }
        );

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteEmpty) {
                    if (!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(view.getText().toString())) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");
                        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                        startActivity(Intent.createChooser(intent, "Send Email"));

                    }
                }else {
                    Toast.makeText(MainActivity.this,"заполните email",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}