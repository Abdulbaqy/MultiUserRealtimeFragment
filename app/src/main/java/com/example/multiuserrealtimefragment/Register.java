package com.example.multiuserrealtimefragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText fullName, email, phoneNumber, password;
    Button registerBtn, gotoLogin;
    boolean valid = true;
    DatabaseReference usersRef;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    CheckBox isVendorRadio, isCustomerRadio;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        usersRef = fDatabase.getReference("Users");

        fullName = findViewById(R.id.ET_fullname);
        email = findViewById(R.id.ET_email);
        phoneNumber = findViewById(R.id.ET_phoneNumber);
        password = findViewById(R.id.ET_password);
        registerBtn = findViewById(R.id.signUpBtn);
        gotoLogin = findViewById(R.id.goToLoginBtn);
        isVendorRadio = findViewById(R.id.isVendorRB);
        isCustomerRadio = findViewById(R.id.isCustomerRB);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        isVendorRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isCustomerRadio.setChecked(false);
                }
            }
        });

        isCustomerRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isVendorRadio.setChecked(false);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkField(fullName);
                checkField(email);
                checkField(phoneNumber);
                checkField(password);


                // Radio button validation
                if (!(isVendorRadio.isChecked() || isCustomerRadio.isChecked())) {
                    Toast.makeText(Register.this, "Select the account type!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valid) {
                    // Start user registration process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    String uid = user.getUid();
                                    DatabaseReference userRef = usersRef.child(uid);
                                    userRef.child("FullName").setValue(fullName.getText().toString());
                                    userRef.child("UserEmail").setValue(email.getText().toString());
                                    userRef.child("PhoneNumber").setValue(phoneNumber.getText().toString());
                                    userRef.child("Password").setValue(password.getText().toString());

                                    String fullNameText = fullName.getText().toString();
                                    String emailText = email.getText().toString();
                                    String phoneNumberText = phoneNumber.getText().toString();
                                    String isCustomerRadioText = isCustomerRadio.isChecked() ? "customer" : "";
                                    String isVendorRadioText = isVendorRadio.isChecked() ? "vendor" : "";

                                    ModelUser modelUser = new ModelUser(fullNameText, emailText, phoneNumberText, isCustomerRadioText, isVendorRadioText);

                                    // Specify if the user is a vendor
                                    if (isVendorRadio.isChecked()) {
                                        userRef.child("as").setValue("vendor");
                                    }
                                    if (isCustomerRadio.isChecked()) {
                                        userRef.child("as").setValue("customer");
                                    }

                                    if (isCustomerRadio.isChecked()) {
                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                        finish();
                                    }
                                    if (isVendorRadio.isChecked()) {
                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                        finish();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Failed to Create Account: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("Register", "Error creating account", e);                                }
                            });
                }
            }
        });

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    public boolean checkField(EditText textfield) {
        if (textfield.getText().toString().isEmpty()) {
            textfield.setError("Error");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }
}