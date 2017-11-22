package example.fragments.HelperPackage;

/**
 * Created by frost on 28.10.2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import example.fragments.ProductPackage.Product;
import example.fragments.R;

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
        viewHolder.imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
        if (viewHolder.checkBoxView != null) {
            viewHolder.checkBoxView.setChecked(product.getFavorite() == 1 ? true : false);
        }
        if (viewHolder.checkBoxView != null) {
            viewHolder.checkBoxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (viewHolder.checkBoxView.isChecked()) {
                        product.setFavorite(1);
                    } else {
                        product.setFavorite(0);
                    }
                }
            });
        }
        return convertView;
    }

    private class ViewHolder{
        final ImageView imageView;
        final TextView nameView;
        final CheckBox checkBoxView;
        ViewHolder(View view){
            nameView = (TextView) view.findViewById(R.id.iName);
            imageView = (ImageView) view.findViewById(R.id.iImage);
            checkBoxView = (CheckBox) view.findViewById(R.id.I_checkBox);
        }
    }
}

