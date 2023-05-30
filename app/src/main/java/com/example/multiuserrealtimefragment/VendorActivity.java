package com.example.multiuserrealtimefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.multiuserrealtimefragment.customer_ui.CustomerChatFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerHomeFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerProfileFragment;
import com.example.multiuserrealtimefragment.customer_ui.CustomerProjectFragment;
import com.example.multiuserrealtimefragment.vendor_ui.VendorChatFragment;
import com.example.multiuserrealtimefragment.vendor_ui.VendorHomeFragment;
import com.example.multiuserrealtimefragment.vendor_ui.VendorProfileFragment;
import com.example.multiuserrealtimefragment.vendor_ui.VendorProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.multiuserrealtimefragment.databinding.ActivityVendorBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class VendorActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    VendorHomeFragment vendorHomeFragment = new VendorHomeFragment();
    VendorChatFragment vendorChatFragment = new VendorChatFragment();
    VendorProjectFragment vendorProjectFragment = new VendorProjectFragment();
    VendorProfileFragment vendorProfileFragment = new VendorProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vendor);

        Button logout = findViewById(R.id.logoutBtn);

        bottomNavigationView = findViewById(R.id.nav_view_vendor);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerVendor, vendorHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home_vendor) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerVendor, vendorHomeFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_chat_vendor) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerVendor, vendorChatFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_project_vendor) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerVendor, vendorProjectFragment).commit();
                    return true;
                } else if (itemId == R.id.navigation_profile_vendor) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerVendor, vendorProfileFragment).commit();
                    return true;
                }
                return false;
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VendorActivity.this, Login.class));
                preferences.clearData(VendorActivity.this);
                finish();
            }
        });


    }

}