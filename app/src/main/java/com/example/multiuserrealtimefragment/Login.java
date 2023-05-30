package com.example.multiuserrealtimefragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.multiuserrealtimefragment.customer_ui.CustomerProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText fullname, password, email;
    Button loginBtn, gotoRegister;
    Switch active;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.ET_email);
        password = findViewById(R.id.ET_LoginPassword);
        loginBtn = findViewById(R.id.signInBtn);
        gotoRegister = findViewById(R.id.goToRegisterBtn);
        active = findViewById(R.id.active);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Login berhasil
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userId = user.getUid();

                                    // Lakukan pengecekan as dan alihkan ke aktivitas yang sesuai
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                String userRole = dataSnapshot.child("as").getValue(String.class);
                                                if (userRole.equals("customer")) {
                                                    preferences.setDataLogin(Login.this, true);
                                                    preferences.setDataAs(Login.this, "customer");

                                                    String nameFromDB = dataSnapshot.child(userId).child("FullName").getValue(String.class);
                                                    String emailFromDB = dataSnapshot.child(userId).child("UserEmail").getValue(String.class);
                                                    String numberFromDB = dataSnapshot.child(userId).child("PhoneNumber").getValue(String.class);
                                                    String asFromDB = dataSnapshot.child(userId).child("as").getValue(String.class);

                                                    CustomerProfileFragment profileFragment = new CustomerProfileFragment();
                                                    Bundle bundle = new Bundle();

                                                    bundle.putString("FullName", nameFromDB);
                                                    bundle.putString("Email", emailFromDB);
                                                    bundle.putString("PhoneNumber", numberFromDB);
                                                    bundle.putString("as", asFromDB);
                                                    profileFragment.setArguments(bundle);

                                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.containerCustomer, profileFragment);
                                                    transaction.commit();

                                                    Intent intent = new Intent(Login.this, CustomerActivity.class);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);

                                                } else if (userRole.equals("vendor")) {
                                                    preferences.setDataLogin(Login.this, true);
                                                    preferences.setDataAs(Login.this, "vendor");
                                                    startActivity(new Intent(Login.this, VendorActivity.class));
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(Login.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    // Login gagal
                                    Toast.makeText(Login.this, "Login gagal. Periksa kembali email dan kata sandi Anda.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)){
            if (preferences.getDataAs(this).equals("customer")){
                startActivity(new Intent(Login.this, CustomerActivity.class));
                finish();
            }else if(preferences.getDataAs(this).equals("vendor")){
                startActivity(new Intent(Login.this, VendorActivity.class));
                finish();
            }
        }
    }
}