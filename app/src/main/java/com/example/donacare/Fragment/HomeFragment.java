package com.example.donacare.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donacare.Adapter.HomeAdapter;
import com.example.donacare.Model.HomeModel;
import com.example.donacare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModels;
    ArrayAdapter adapter;
    HomeAdapter homeAdapter;

    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        SearchView searchView = (SearchView) view.findViewById(R.id.simpleSearchView);
//        EditText searchEditText = (EditText) searchView.findViewById(com.google.android.material.R.id.search_src_text);
//        searchEditText.setTextColor(getResources().getColor(R.color.white));
//        searchEditText.setHintTextColor(getResources().getColor(R.color.white));


        setHasOptionsMenu(true);

        homeModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<HomeAdapter>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

        recyclerView = view.findViewById(R.id.rvListHome);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("2", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("3", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("4", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("5", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("6", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));

        homeAdapter = new HomeAdapter(homeModels);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;

    }

    private void getData() {
        homeModels = new ArrayList<>();
    }

}
