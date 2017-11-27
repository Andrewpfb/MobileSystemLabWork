package example.game.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import example.game.R;
import example.game.model.Example;
import example.game.model.Player;
import example.game.utils.JsonHelper;
import example.game.utils.Settings;
import example.game.utils.ExampleGenerator;

/**
 * Created by frost on 26.11.2017.
 */

public class GameFragment extends Fragment {
    private Button startBtn, checkBtn;
    private Chronometer timer;
    private TextView example;
    private EditText result;




    private Example currentExample;
    private Integer duration;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.game_fragment, container, false);

        startBtn = (Button) view.findViewById(R.id.gf_btnStart);
        checkBtn = (Button) view.findViewById(R.id.gf_btnCheck);
        timer = (Chronometer) view.findViewById(R.id.gf_timer);
        example = (TextView) view.findViewById(R.id.gf_example);
        result = (EditText) view.findViewById(R.id.gf_result);
        duration = Settings.GetTimerDuration(view.getContext()) * 1000;
        Settings.Score = 0;
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - timer.getBase();
                if (elapsedMillis >= duration) {
                    result.setEnabled(false);
                    checkBtn.setEnabled(false);
                    startBtn.setVisibility(View.VISIBLE);
                    timer.stop();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
                    ft.replace(R.id.container, new ResultFragment());
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                startBtn.setVisibility(View.INVISIBLE);
                currentExample = ExampleGenerator.GetExample();
                example.setText(currentExample.getExample());
            }
        });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getText().length() != 0) {
                    if (currentExample.getResult() == Integer.parseInt(result.getText().toString())) {
                        Settings.Score++;
                        result.setText("");
                        currentExample = ExampleGenerator.GetExample();
                        example.setText(currentExample.getExample());
                    } else {
                        result.setText("");
                        currentExample = ExampleGenerator.GetExample();
                        example.setText(currentExample.getExample());
                    }
                } else {
                    result.setText("");
                    currentExample = ExampleGenerator.GetExample();
                    example.setText(currentExample.getExample());
                }
            }
        });
        return view;
    }
}
