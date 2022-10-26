package vcpe.st10118615.mooiste.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import io.paperdb.Paper;
import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.model.Order;
import vcpe.st10118615.mooiste.model.Product;
import vcpe.st10118615.mooiste.util.Utils;

public class ProductDetailsActivity  extends AppCompatActivity {
    private CardView addToCartBtn;
    private ImageView productImg;
    private TextView plusBTn,minusBtn,quantityTV;
    private TextView productName,productDescription,price;
    private Product product;
    private int quantity = 1;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initAll();
        ClickListeners();
        product= (Product) getIntent().getSerializableExtra("product");
        if(product.getPhotoUrl()!= null){
            if(!product.getPhotoUrl().equals("")){
                // TODO RENAME LOGO
                Picasso.get().load(product.getPhotoUrl()).placeholder(R.drawable.mooiste_clothing_port_elizabeth).into(productImg);
            }
        }
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        price.setText("R "+product.getPrice());
    }

    private void ClickListeners() {
        plusBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                quantityTV.setText(String.valueOf(quantity));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity > 1){
                    quantity -= 1;
                    quantityTV.setText(String.valueOf(quantity));
                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInCart = false;
                for(int i = 0;i < order.getCartProductList().size(); i++){
                    if(product.getProductId().equals(order.getCartProductList().get(i).getProductId())){
                        isInCart = true;
                        break;
                    }
                }
                if(!isInCart){
                    product.setQuantityInCart(quantity);
                    order.addProduct(product);
                    Log.d("testorder",order.getTotalPrice() + "");
                    Paper.book().delete("order");
                    Paper.book().write("order",order);
                    Toast.makeText(ProductDetailsActivity.this,"Added to cart",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(ProductDetailsActivity.this,"Already in cart",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initAll() {
        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        productImg = findViewById(R.id.product_img);
        plusBTn = findViewById(R.id.plus_btn);
        minusBtn = findViewById(R.id.minus_btn);
        quantityTV = findViewById(R.id.quantity_tv);
        productName = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);

        product = new Product();

        order = new Order();

        if(Paper.book().read("order")!=null){
            order=Paper.book().read("order");
        }
        Utils.statusBarColor(ProductDetailsActivity.this);
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Paper.book().read("order") != null){
            order=Paper.book().read("order");
        }
    }

}
