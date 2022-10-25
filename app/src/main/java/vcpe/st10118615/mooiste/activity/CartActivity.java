package vcpe.st10118615.mooiste.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vcpe.st10118615.mooiste.adapter.CartCustomAdapter;
import vcpe.st10118615.mooiste.model.Order;
import vcpe.st10118615.mooiste.model.Product;

public class CartActivity {
    public static TextView totalPriceView;
    private ImageView deletCart,cartBackArrow;
    private CardView checkOut;
    private Order order;
    private RecyclerView cartRecyclerView;
    private CartCustomAdapter cartCustomAdapter;
    private ArrayList<Product> productArrayList;
    private static double totalPrice;

}
