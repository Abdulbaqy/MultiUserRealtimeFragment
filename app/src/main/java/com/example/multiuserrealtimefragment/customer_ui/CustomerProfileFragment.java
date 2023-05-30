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
import com.example.multiuserrealtimefragment.R;

public class CustomerProfileFragment extends Fragment {

    TextView customerProfileName, customerProfileEmail, customerProfileNumber, customerProfileAs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_fragment_profile, container, false);

        Button logoutBtn;

        showUserData();

        return root;
    }
    public void showUserData(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            TextView customerProfileName = getView().findViewById(R.id.profile_customer_name);
            TextView customerProfileEmail = getView().findViewById(R.id.profile_customer_email);
            TextView customerProfileNumber = getView().findViewById(R.id.profile_customer_number);
            TextView customerProfileAs = getView().findViewById(R.id.profile_customer_as);

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
