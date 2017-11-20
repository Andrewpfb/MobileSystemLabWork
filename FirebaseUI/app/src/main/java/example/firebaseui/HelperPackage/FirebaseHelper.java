package example.firebaseui.HelperPackage;


/**
 * Created by frost on 13.11.2017.
 */

import android.provider.ContactsContract;
import android.widget.Toast;

import example.firebaseui.ProductPackage.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private static FirebaseAuth mAuth;
    private static FirebaseUser user;
    private static DatabaseReference myRef;
    private static Query query;
    private static Product product;

    public static void Write(Product product){
        user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child(user.getUid()).child("Products").push().setValue(product);
    }
}
