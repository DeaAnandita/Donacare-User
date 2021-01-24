package com.example.donacare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donacare.Adapter.HomeAdapter;
import com.example.donacare.Model.HomeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModels;
    ArrayAdapter adapter;
    HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);


//        setHasOptionsMenu(true);
//
//        homeModels = new ArrayList<>();
//        getData();
//        adapter = new ArrayAdapter<HomeAdapter>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
//
//        recyclerView = recyclerView.findViewById(R.id.rvListHome);
//
//        homeModels = new ArrayList<>();
//        homeModels.add(new HomeModel(""));
//        homeModels.add(new HomeModel("2", "Noodle"));
//        homeModels.add(new HomeModel("3", "Soup"));
//        homeModels.add(new HomeModel("4", "Cake"));
//        homeModels.add(new HomeModel("5", "Cookie"));
//        homeModels.add(new HomeModel("6", "Salad"));
//
//        homeAdapter = new HomeAdapter(homeModels);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(homeAdapter);
//
//    }
//
//    private void getData() {
//        homeModels = new ArrayList<>();
//    }
    }
}
