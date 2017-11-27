package example.game.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.IOException;

import example.game.MainActivity;
import example.game.R;
import example.game.utils.Settings;

/**
 * Created by frost on 26.11.2017.
 */

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    View view;
    Integer[] timer = {30,60,90};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment,container,false);
        Spinner spinner = (Spinner)view.findViewById(R.id.sf_spinnerTimer);
        Button clearBtn = (Button) view.findViewById(R.id.sf_clearBtn);
        Button saveBtn = (Button) view.findViewById(R.id.sf_btnSave);
        android.support.v7.widget.SwitchCompat switch1 = (android.support.v7.widget.SwitchCompat) view.findViewById(R.id.switch_compat);
        if(switch1!=null){
            switch1.setOnCheckedChangeListener(this);
            switch1.setChecked(Settings.GetSound(view.getContext())==1?true:false);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(),android.R.layout.simple_spinner_item,timer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(Settings.GetPositionSpinner(view.getContext()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Settings.SetTimerDuration(view.getContext(),Integer.parseInt(parent.getSelectedItem().toString()));
                Settings.SetPositionSpinner(view.getContext(),position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Settings.ClearResults(view.getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Settings.SetSound(view.getContext(),isChecked?1:0);
    }

}
