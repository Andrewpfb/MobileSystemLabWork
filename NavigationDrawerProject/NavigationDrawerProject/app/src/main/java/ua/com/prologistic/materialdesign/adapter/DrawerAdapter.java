package ua.com.prologistic.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ua.com.prologistic.materialdesign.model.ItemModel;
import ua.com.prologistic.navigationdrawerproject.R;

public class DrawerAdapter extends ArrayAdapter<ItemModel> {

    Context mContext;
    int layoutId;
    ItemModel data[] = null;

    public DrawerAdapter(Context mContext, int layoutId, ItemModel[] data) {

        super(mContext, layoutId, data);
        this.layoutId = layoutId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutId, parent, false);

        ImageView imageIcon = (ImageView) listItem.findViewById(R.id.imageIcon);
        TextView mName = (TextView) listItem.findViewById(R.id.mName);

        // получаем данные из переданого массива
        ItemModel model = data[position];

        imageIcon.setImageResource(model.icon);
        mName.setText(model.name);

        return listItem;
    }
}