package example.game.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.game.R;
import example.game.model.Player;
import example.game.utils.JsonHelper;
import example.game.utils.Settings;

/**
 * Created by frost on 26.11.2017.
 */

public class ResultFragment extends Fragment {
    private View view;
    private EditText editText;
    private String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_fragment,container,false);
        editText = (EditText)view.findViewById(R.id.rf_name);
        TextView textView = (TextView)view.findViewById(R.id.rf_result);
        textView.setText(Settings.Score.toString());
        Button button = (Button)view.findViewById(R.id.rf_btnOk);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddPlayer();
            }
        });
        return view;
    }
    private void AddPlayer(){
        name = editText.getText().toString();
        if(Settings.playerList==null){
            Settings.playerList = new ArrayList<>();
        }
        Settings.playerList.add(new Player(name,Settings.Score));
        if(JsonHelper.exportToJSON(view.getContext())){
            Toast.makeText(view.getContext(), "Player was added", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            ft.replace(R.id.container, new GameFragment());
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
