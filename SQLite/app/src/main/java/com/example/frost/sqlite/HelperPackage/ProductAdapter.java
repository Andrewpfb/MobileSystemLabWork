package com.example.frost.sqlite.HelperPackage;

/**
 * Created by frost on 28.10.2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;

    public ProductAdapter(Context context, int resource, List<Product> products){
        super(context,resource,products);
        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);
        TextView name = (TextView) view.findViewById(R.id.iName);
        TextView category = (TextView) view.findViewById(R.id.iCategory);
        TextView price = (TextView) view.findViewById(R.id.iPrice);
        TextView count = (TextView) view.findViewById(R.id.iCount);
        ImageView imageView = (ImageView) view.findViewById(R.id.iImage);

        Product product = products.get(position);

        name.setText(product.getName());
        category.setText(product.getCategory());
        price.setText(product.getPrice().toString());
        count.setText(product.getCount().toString());
        imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));

        return view;
    }
}
