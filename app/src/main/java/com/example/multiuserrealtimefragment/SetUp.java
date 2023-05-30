package com.example.multiuserrealtimefragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.multiuserrealtimefragment.customer_ui.CustomerHomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetUp extends AppCompatActivity {

    private EditText uploadSize, uploadAddress, uploadCity;
    private Button submitSetupBtn;
    private RadioButton RBsetupNatural, RBsetupIwagumi, RBsetupDutch;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        uploadSize = findViewById(R.id.ETSetup_size);
        uploadAddress = findViewById(R.id.ETSetup_adress);
        uploadCity = findViewById(R.id.ETSetup_city);
        RBsetupNatural = findViewById(R.id.radioButtonNatural);
        RBsetupDutch = findViewById(R.id.radioButtonDutch);
        RBsetupIwagumi = findViewById(R.id.radioButtonIwagumi);
        submitSetupBtn = findViewById(R.id.submitBtnSetup);

        RBsetupIwagumi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBsetupDutch.setChecked(false);
                    RBsetupNatural.setChecked(false);
                }
            }
        });

        RBsetupDutch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBsetupIwagumi.setChecked(false);
                    RBsetupNatural.setChecked(false);
                }
            }
        });

        RBsetupNatural.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBsetupIwagumi.setChecked(false);
                    RBsetupDutch.setChecked(false);
                }
            }
        });


        submitSetupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getDataSize = uploadSize.getText().toString();
                String getDataAddress = uploadAddress.getText().toString();
                String getDataCity = uploadCity.getText().toString();

                if(getDataSize.isEmpty()){
                    uploadSize.setError("Masukkan Ukuran!");
                }else if(getDataAddress.isEmpty()) {
                    uploadSize.setError("Masukkan Alamat Lengkap!");
                }else if(getDataCity.isEmpty()) {
                    uploadCity.setError("Masukkan Kota!");
                }else{
                    reference.child("Set Up").push().setValue(new ModelSetUp(getDataSize, getDataAddress, getDataCity)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SetUp.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerCustomer, new CustomerHomeFragment());
                            fragmentTransaction.commit();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SetUp.this, "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}