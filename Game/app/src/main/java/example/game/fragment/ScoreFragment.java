package example.game.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ScoreFragment extends Fragment {
    private ArrayAdapter<Player> adapter;
    private View view;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.score_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.scf_playersList);
        GetData();
        return view;
    }
    private void GetData(){
       Settings.playerList = JsonHelper.importFromJSON(view.getContext());
        if(Settings.playerList!=null){
            adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, Settings.playerList);
            listView.setAdapter(adapter);
            Toast.makeText(view.getContext(), "Data received", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(view.getContext(), "While nobody played", Toast.LENGTH_SHORT).show();
        }
    }
}
