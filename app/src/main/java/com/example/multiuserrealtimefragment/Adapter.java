package com.example.multiuserrealtimefragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter {
//    private List<Object> mlist ;
//
//    private static final int VIEW_TYPE_SETUP = 1;
//    private static final int VIEW_TYPE_MAINTENANCE = 2;
//
//    private Activity activity;
//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//    public Adapter(List<Object>mlist){
//        this.mlist = mlist;
//        this.activity = activity;
//    }
//
//    public int getItemViewType(int position) {
//        Object item = mlist.get(position);
//
//        if (item instanceof SetUp) {
//            return VIEW_TYPE_SETUP;
//        } else if (item instanceof Maintenance) {
//            return VIEW_TYPE_MAINTENANCE;
//        }
//        return super.getItemViewType(position);
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        if (viewType == VIEW_TYPE_SETUP) {
//            View view = inflater.inflate(R.layout.card_project, parent, false);
//            return new SetUpViewHolder(view);
//        } else if (viewType == VIEW_TYPE_MAINTENANCE) {
//            View view = inflater.inflate(R.layout.card_projectmain, parent, false);
//            return new MaintenanceViewHolder(view);
//        }
//
//        throw new IllegalArgumentException("Invalid view type: " + viewType);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        final Object item = mlist.get(position);
//        if (holder instanceof SetUpViewHolder && item instanceof SetUp) {
//            DataClass dataClass = (DataClass) item;
//            SetUpViewHolder setUpViewHolder = (SetUpViewHolder) holder;
//            setUpViewHolder.bind(dataClass);
//        } else if (holder instanceof MaintenanceViewHolder && item instanceof Maintenance) {
//            DataClassMaintenance dataClassMaintenance = (DataClassMaintenance) item;
//            MaintenanceViewHolder maintenanceViewHolder = (MaintenanceViewHolder) holder;
//            maintenanceViewHolder.bind(dataClassMaintenance);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mlist.size();
//    }
//
//    public void showData() {
//        reference.child("Set Up").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
//                    if (dataClass != null) {
//                        mlist.add(dataClass);
//                        notifyItemInserted(mlist.size() - 1);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        reference.child("Maintenance").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    DataClassMaintenance dataClassMaintenance = dataSnapshot.getValue(DataClassMaintenance.class);
//                    if (dataClassMaintenance != null) {
//                        mlist.add(dataClassMaintenance);
//                        notifyItemInserted(mlist.size() - 1);
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
//}



}
