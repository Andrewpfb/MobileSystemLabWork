package example.webcontent;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProgressFragment extends Fragment{
    View view;

    TextView contentView;
    RecyclerView recyclerView;
    List<News> newses;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_progress, container, false);
        contentView = (TextView) view.findViewById(R.id.progressText);
        contentView.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        new ProgressTask().execute("https://habrahabr.ru/rss/interesting/");
        return view;
    }

    private class ProgressTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... path) {

            if(newses==null){
                newses = new ArrayList<>();
            }

            try {
                SaxFeedParser saxFeedParser = new SaxFeedParser(path[0]);
                newses = saxFeedParser.parse();
            }catch (Exception e){}
            return null;
        }
        @Override
        protected void onPostExecute(Void a) {
            contentView.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Данные загружены", Toast.LENGTH_SHORT)
                    .show();
            newses.remove(0);
            DataAdapter dataAdapter = new DataAdapter(view.getContext(),newses);
            dataAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View v,int position){
                    StaticLinkStorage.link = newses.get(position).getLink().toString();
                    Intent intent = new Intent(view.getContext(),ViewPageActivity.class);
                    intent.putExtra("link",newses.get(position).getLink().toString());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(dataAdapter);
        }
    }
}