package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class dang_ky extends AppCompatActivity {

    EditText iedtEmail2, iedtPassword2, iedtUsername;
    Button btnsignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);

        mAuth = FirebaseAuth.getInstance();

        iedtUsername = findViewById(R.id.editText2);
        iedtEmail2 = findViewById(R.id.input_email);
        iedtPassword2 = findViewById(R.id.input_passwd);
        btnsignUp = findViewById(R.id.button2);

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){
        String email2, password2, username;
        email2 = iedtEmail2.getText().toString().trim();
        password2 = iedtPassword2.getText().toString().trim();
        username = iedtUsername.getText().toString().trim();
        //kiểm tra kết email2, password2, username không empty
        if (TextUtils.isEmpty(email2)) {
            Toast.makeText(dang_ky.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(dang_ky.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        } else if (password2.length() < 7) {

            Toast.makeText(dang_ky.this, "Password should greater than 7 digits", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(dang_ky.this, "Please enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email2, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //FirebaseUser user = mAuth.getCurrentUser();
                    // FirebaseDatabase.getInstance().getReference("users")
                    //         .child(user.getUid()).child("username").setValue(username);
                    Toast.makeText(getApplicationContext(), "Sign up successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dang_ky.this, Dang_nhap.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
