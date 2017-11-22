package example.fragments.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.fragments.HelperPackage.BitmapHelper;
import example.fragments.ProductPackage.Product;
import example.fragments.R;

/**
 * Created by frost on 20.11.2017.
 */

public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
    public void setObject(Product product){
        TextView name = (TextView) getView().findViewById(R.id.FD_nameTV);
        name.setText("Name: "+product.getName());
        TextView category = (TextView) getView().findViewById(R.id.FD_categoryTV);
        category.setText("Category: "+product.getCategory());
        TextView price = (TextView) getView().findViewById(R.id.FD_priceTV);
        price.setText("Price: "+product.getPrice().toString());
        TextView count = (TextView) getView().findViewById(R.id.FD_countTV);
        count.setText("Count: "+product.getCount().toString());
        ImageView image = (ImageView) getView().findViewById(R.id.FD_imgView);
        image.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
    }
}
