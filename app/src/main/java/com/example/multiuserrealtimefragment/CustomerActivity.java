package com.example.multiuserrealtimefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;

import com.example.multiuserrealtimefragment.customer_ui.CustomerChatFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerHomeFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerProfileFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.multiuserrealtimefragment.databinding.ActivityCustomerBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CustomerHomeFragment customerHomeFragment = new CustomerHomeFragment();
    CustomerChatFragment customerChatFragment = new CustomerChatFragment();
    CustomerProjectFragment customerProjectFragment = new CustomerProjectFragment();
    CustomerProfileFragment customerProfileFragment = new CustomerProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        bottomNavigationView = findViewById(R.id.nav_view_customer);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerCustomer, customerHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home_customer) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerCustomer, customerHomeFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_chat_customer) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerCustomer, customerChatFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_project_customer) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerCustomer, customerProjectFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_profile_customer) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerCustomer, customerProfileFragment).commit();
                    return true;
                }
                return false;
            }
        });



    }

}