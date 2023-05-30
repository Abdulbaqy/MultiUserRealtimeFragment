package com.example.multiuserrealtimefragment.customer_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.multiuserrealtimefragment.Maintenance;
import com.example.multiuserrealtimefragment.R;
import com.example.multiuserrealtimefragment.SetUp;

public class CustomerHomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_fragment_home, container, false);

        Button setUpbtn = root.findViewById(R.id.SetupBtn);
        Button maintenancebtn = root.findViewById(R.id.MaintenanceBtn);

        setUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SetUp.class);
                startActivity(intent);
            }
        });

        maintenancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), Maintenance.class);
                startActivity(intent);
            }
        });
        return root;
    }
}