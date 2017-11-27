package example.game.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.game.R;
import example.game.utils.Settings;

/**
 * Created by frost on 26.11.2017.
 */

public class HelpFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.help_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textView4);
        textView.setText("5. Solve as much as you can in "+Settings.GetTimerDuration(view.getContext())+" seconds!");
        return view;
    }
}
