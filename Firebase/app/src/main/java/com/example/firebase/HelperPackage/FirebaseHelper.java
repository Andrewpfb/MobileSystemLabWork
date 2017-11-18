package com.example.firebase.HelperPackage;


/**
 * Created by frost on 13.11.2017.
 */

import com.example.firebase.ProductPackage.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseAuth mAuth;
    private static FirebaseUser user;
    private static DatabaseReference myRef;
    public static void Write(Product product){
        user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child(user.getUid()).child("Products").push().setValue(product);
    }
}
