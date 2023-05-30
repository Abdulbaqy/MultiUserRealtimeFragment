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

public class Maintenance extends AppCompatActivity {

    private EditText uploadMaintenanceSize, uploadMaintenanceAddress, uploadMaintenanceCity;
    private Button submitMaintenanceBtn;
    private RadioButton RBlow, RBmid, RBhigh;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        uploadMaintenanceSize = findViewById(R.id.ETMaintenance_size);
        uploadMaintenanceAddress = findViewById(R.id.ETMaintenance_address);
        uploadMaintenanceCity = findViewById(R.id.ETMaintenance_city);
        RBlow = findViewById(R.id.radioButtonLow);
        RBmid = findViewById(R.id.radioButtonMid);
        RBhigh = findViewById(R.id.radioButtonHigh);
        submitMaintenanceBtn = findViewById(R.id.submitBtnMaintenance);

        RBlow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBmid.setChecked(false);
                    RBhigh.setChecked(false);
                }
            }
        });

        RBmid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBlow.setChecked(false);
                    RBhigh.setChecked(false);
                }
            }
        });

        RBhigh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    RBlow.setChecked(false);
                    RBmid.setChecked(false);
                }
            }
        });

        submitMaintenanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getDataSizeMaintenance = uploadMaintenanceSize.getText().toString();
                String getDataAddressMaintenance = uploadMaintenanceAddress.getText().toString();
                String getDataCityMaintenance = uploadMaintenanceCity.getText().toString();

                if (getDataSizeMaintenance.isEmpty()) {
                    uploadMaintenanceSize.setError("Masukkan Ukuran!");
                } else if (getDataAddressMaintenance.isEmpty()) {
                    uploadMaintenanceAddress.setError("Masukkan Alamat Lengkap!");
                } else if (getDataCityMaintenance.isEmpty()) {
                    uploadMaintenanceCity.setError("Masukkan Kota!");
                } else {
                    reference.child("Maintenance").push().setValue(new ModelMaintenance(getDataSizeMaintenance, getDataAddressMaintenance, getDataCityMaintenance)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Maintenance.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.containerCustomer, new CustomerHomeFragment());
                            fragmentTransaction.commit();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Maintenance.this, "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}