package example.fragments.Activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import example.fragments.HelperPackage.ProductAdapter;
import example.fragments.ProductPackage.Product;
import example.fragments.R;

/**
 * Created by frost on 20.11.2017.
 */

public class ListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ListView listView;
    private static FirebaseAuth mAuth;
    private static DatabaseReference myRef;
    private List<Product> products;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        if(products==null){
            products = new ArrayList<>();
        }
        listView = (ListView)view.findViewById(R.id.FL_ProductsList);
        ShowFunction();
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Product selProduct = (Product)parent.getItemAtPosition(position);
                ShowDetails(selProduct);
            }
        };
        listView.setOnItemClickListener(itemListener);
        registerForContextMenu(listView);
        return view;
    }

    private void ShowFunction() {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("Products");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                products.add(dataSnapshot.getValue(Product.class));
                updateUI();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);
    }

    private void updateUI() {
        productAdapter = new ProductAdapter(getView().getContext(),R.layout.item,products);
        listView.setAdapter(productAdapter);
    }

    private void ShowDetails(Product selProduct) {
        mListener.onFragmentInteraction(selProduct);
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(Product product);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implements interface OnFragmentInteractionListener");
        }
    }
}
