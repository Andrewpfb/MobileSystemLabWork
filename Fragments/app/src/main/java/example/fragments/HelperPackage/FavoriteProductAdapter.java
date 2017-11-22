package example.fragments.HelperPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.fragments.ProductPackage.Product;
import example.fragments.R;

/**
 * Created by frost on 20.11.2017.
 */

public class FavoriteProductAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;

    public FavoriteProductAdapter(Context context, int resource, List<Product> products){
        super(context,resource,products);
        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Product product = products.get(position);

        viewHolder.nameView.setText(product.getName());
        viewHolder.categoryView.setText(product.getCategory());
        viewHolder.priceView.setText(product.getPrice().toString());
        viewHolder.countView.setText(product.getCount().toString());
        viewHolder.imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
        return convertView;
    }

    private class ViewHolder{
        final ImageView imageView;
        final TextView nameView,categoryView, priceView, countView;
        ViewHolder(View view){
            nameView = (TextView) view.findViewById(R.id.iName);
            categoryView = (TextView) view.findViewById(R.id.iCategory);
            priceView = (TextView) view.findViewById(R.id.iPrice);
            countView = (TextView) view.findViewById(R.id.iCount);
            imageView = (ImageView) view.findViewById(R.id.iImage);
        }
    }
}
