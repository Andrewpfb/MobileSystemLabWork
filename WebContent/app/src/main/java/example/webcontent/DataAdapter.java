package example.webcontent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<News> news;
    private CardView cardView;
    public static OnItemClickListener listener;


    DataAdapter(Context context, List<News> news) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        News oneNews = news.get(position);
        holder.titleView.setText(formatString(oneNews.getTitle()));
        holder.dateTimeView.setText(oneNews.getDateTime());
        holder.linkView.setText(oneNews.getLink().toString());
    }

    private String formatString(String fString){
        String formattedString=fString.replaceAll("\\p{Cntrl}", "");
        int n=0;
        for (char ch : fString.toCharArray()) {
            if((int)ch==32){
                n++;
            }
            if ( Character.isLetterOrDigit(ch) ) {
                formattedString = formattedString.substring(n);
                return formattedString;
            }
        }
        return formattedString;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView titleView, dateTimeView, linkView;
        View view;
        ViewHolder(final View view){
            super(view);
            this.view = view;
            titleView = (TextView) view.findViewById(R.id.li_Title);
            dateTimeView = (TextView) view.findViewById(R.id.li_DateTime);
            linkView = (TextView) view.findViewById(R.id.li_Link);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(DataAdapter.listener!=null){
                        DataAdapter.listener.onItemClick(view,getLayoutPosition());
                    }
                }
            });
        }
    }
}
