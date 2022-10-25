package vcpe.st10118615.mooiste.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.activity.CustomersOrders;
import vcpe.st10118615.mooiste.activity.LoginActivity;
import vcpe.st10118615.mooiste.util.Utils;

public class AdminHome extends AppCompatActivity {
    private int counter = 0;
    private RelativeLayout logoutBtn, newProduct, viewProduct, viewCustomersOrders;
    private FirebaseAuth mAuth;
    DatabaseReference myRootRef;
    private TextView orderCount;
    private ProgressBar countProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Paper.init(AdminHome.this);

        initAll();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Paper.book().delete("active");
                Intent intent = new Intent(AdminHome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        newProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
        viewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, ViewAllProductsActivity.class);
                startActivity(intent);
            }
        });
        viewCustomersOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, CustomersOrders.class);
                intent.putExtra("type", "admin");
                startActivity(intent);
            }
        });
        getOrdersCount();
    }

    public void getOrdersCount() {
        countProgressBar.setVisibility(View.VISIBLE);
        myRootRef.child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("Testchildcount", dataSnapshot.getChildrenCount() + "");
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String id = child.getKey();
                        getOrders(id);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void getOrders(String id) {
        myRootRef.child("Order").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        counter++;
                        Log.d("counter", counter + "");
                        orderCount.setText(counter + "");
                    }
                    countProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    // TODO: ADD LOGIC
    @Override
    public void onBackPressed() {

    }

    private void initAll() {
        logoutBtn = findViewById(R.id.logout_btn);
        newProduct = findViewById(R.id.new_product_layout);
        viewProduct = findViewById(R.id.view_all_products);
        orderCount = findViewById(R.id.orders_count);
        countProgressBar = findViewById(R.id.order_count_progress);
        viewCustomersOrders = findViewById(R.id.view_customer_order);
        myRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Utils.statusBarColor(AdminHome.this);
    }
}