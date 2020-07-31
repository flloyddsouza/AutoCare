package com.example.autoCare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autoCare.Model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {


    private EditText emailText,passwordText;
    private String password;
    private ProgressBar progressBar;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Login = findViewById(R.id.login);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.GONE);
        userList = new ArrayList<>();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = emailText.getText().toString();

                try {
                    password = hashPassword(passwordText.getText().toString());
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
                Log.i("Flloyd: ", "Email:" + e);
                Log.i("Flloyd: ", "Password:" + password);

                if(validate()){
                    progressBar.setVisibility(View.VISIBLE);
                    Query query = FirebaseDatabase.getInstance().getReference("User").orderByChild("email").equalTo(e);
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            }
        });

        TextView Register = findViewById(R.id.link_signup);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }

    public boolean validate() {

        boolean valid = true;
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        TextInputLayout emailLayout = findViewById(R.id.input_layout_email);
        TextInputLayout passwordLayout = findViewById(R.id.input_layout_password);

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Enter a valid email address");
            valid = false;
        } else {
            emailLayout.setError(null);
        }
        if (password.isEmpty()) {
            passwordLayout.setError("Password cannot be empty");
            valid = false;
        }else if(password.length() < 6 )
        {
            passwordLayout.setError("Alpha numeric character greater than 6");
            valid = false;
        }
        else {
            passwordLayout.setError(null);
        }
        return valid;
    }

    public String hashPassword(@NonNull @NotNull String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hash = new StringBuilder();
        for (byte b : hashInBytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            userList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
            }

            if(userList.isEmpty()){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_SHORT).show();
            }else{
                if(userList.get(0).getPassword().equals(password)){
                    progressBar.setVisibility(View.GONE);
                    Intent intent =  new Intent(Login.this, AutoCare.class);
                    startActivity(intent);
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    };
}