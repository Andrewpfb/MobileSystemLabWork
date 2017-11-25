package ua.com.prologistic.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.com.prologistic.navigationdrawerproject.R;
import ua.com.prologistic.materialdesign.adapter.DataAdapter;
import ua.com.prologistic.materialdesign.model.Phone;

public class GridFragment extends Fragment {

    List<Phone> phones;
    public GridFragment() {
        phones = new ArrayList<>();
        phones.add(new Phone("Huawei P10", "Huawei", R.drawable.mate8));
        phones.add(new Phone("Elite z3", "HP", R.drawable.lumia950));
        phones.add(new Phone("Galaxy S8", "Samsung", R.drawable.galaxys6));
        phones.add(new Phone("LG G 5", "LG", R.drawable.nexus5x));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_g);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        DataAdapter adapter = new DataAdapter(view.getContext(), phones);
        recyclerView.setAdapter(adapter);
        return view;
    }
}