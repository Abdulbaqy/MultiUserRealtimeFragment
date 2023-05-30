package com.example.multiuserrealtimefragment.vendor_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.multiuserrealtimefragment.Login;
import com.example.multiuserrealtimefragment.R;
import com.example.multiuserrealtimefragment.preferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VendorProfileFragment extends Fragment {

    TextView vendorProfileName, vendorProfileEmail, vendorProfileNumber, vendorProfileAs;
    Button logout;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vendor_fragment_profile, container, false);

        vendorProfileName = root.findViewById(R.id.profile_vendor_name);
        vendorProfileEmail = root.findViewById(R.id.profile_vendor_email);
        vendorProfileNumber = root.findViewById(R.id.profile_vendor_number);
        vendorProfileAs = root.findViewById(R.id.profile_vendor_as);
        logout = root.findViewById(R.id.logoutBtn);

        showUserData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);

                preferences.clearData(getActivity());

                requireActivity().finish();
            }
        });

        return root;
    }
    public void showUserData(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            String nameUser = bundle.getString("FullName");
            String emailUser = bundle.getString("UserEmail");
            String numberUser = bundle.getString("PhoneNumber");
            String asUser = bundle.getString("as");

            vendorProfileName.setText(nameUser);
            vendorProfileEmail.setText(emailUser);
            vendorProfileNumber.setText(numberUser);
            vendorProfileAs.setText(asUser);
        }
    }
}