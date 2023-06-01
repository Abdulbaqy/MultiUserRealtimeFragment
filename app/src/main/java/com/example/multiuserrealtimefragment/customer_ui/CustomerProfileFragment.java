package com.example.multiuserrealtimefragment.customer_ui;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.multiuserrealtimefragment.CustomerActivity;
import com.example.multiuserrealtimefragment.Login;
import com.example.multiuserrealtimefragment.R;
import com.example.multiuserrealtimefragment.preferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfileFragment extends Fragment {

    TextView customerProfileName, customerProfileEmail, customerProfileNumber, customerProfileAs;
    Button logout;
//    coba ambil profile
    FirebaseDatabase database;
    FirebaseAuth auth;
    String email, password, name, number, as;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        customerProfileName = root.findViewById(R.id.profile_customer_name);
        customerProfileEmail = root.findViewById(R.id.profile_customer_email);
        customerProfileNumber = root.findViewById(R.id.profile_customer_number);
        customerProfileAs = root.findViewById(R.id.profile_customer_as);
        logout = root.findViewById(R.id.logoutBtn);


        showUserData();

        DatabaseReference reference = database.getReference().child("Users").child(auth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("FullName").getValue().toString();
                number = snapshot.child("PhoneNumber").getValue().toString();
                email = snapshot.child("UserEmail").getValue().toString();
                as = snapshot.child("as").getValue().toString();

                customerProfileName.setText(name);
                customerProfileNumber.setText(number);
                customerProfileEmail.setText(email);
                customerProfileAs.setText(as);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

            customerProfileName.setText(nameUser);
            customerProfileEmail.setText(emailUser);
            customerProfileNumber.setText(numberUser);
            customerProfileAs.setText(asUser);
        }
    }


}
