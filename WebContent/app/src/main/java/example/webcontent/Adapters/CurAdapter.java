package example.webcontent.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.webcontent.Models.Currencies;
import example.webcontent.R;

/**
 * Created by frost on 05.12.2017.
 */

public class CurAdapter extends RecyclerView.Adapter<CurAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Currencies> currencies;


    public CurAdapter(Context context, List<Currencies> news) {
        this.currencies = news;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CurAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.cur_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurAdapter.ViewHolder holder, int position) {
        Currencies oneCurrency = currencies.get(position);
        holder.nameView.setText(oneCurrency.getCur_Name());
        holder.rateView.setText(oneCurrency.getCur_OfficialRate().toString());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, rateView;
        View view;

        ViewHolder(final View view) {
            super(view);
            this.view = view;
            nameView = (TextView) view.findViewById(R.id.cli_name);
            rateView = (TextView) view.findViewById(R.id.cli_rate);
        }
    }
}
