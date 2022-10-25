package vcpe.st10118615.mooiste.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.activity.ProductDetailsActivity;
import vcpe.st10118615.mooiste.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>{

    private List<Product> productList;
    private Activity context;
    private boolean isAdmin;

    public ProductsAdapter(List<Product> usersList, Activity context,boolean isAdmin) {
        this.productList = usersList;
        this.context = context;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_product_layout, parent, false);
        return new ProductsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyViewHolder holder, int position) {

        Product product = productList.get(position);

        if(product.getPhotoUrl()!=null){
            if(!product.getPhotoUrl().equals("")){
                holder.productImg.setVisibility(View.VISIBLE);
                Picasso.get().load(product.getPhotoUrl()).placeholder(R.drawable.mooiste_clothing_port_elizabeth).into(holder.productImg);
            }
        }
        holder.name.setText(product.getName());
        holder.price.setText("R"+product.getPrice());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAdmin){
                    Intent intent=new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product", product);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private ImageView productImg;
        private TextView name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            productImg = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.product_brand_name);
            price = itemView.findViewById(R.id.price_tv);
        }
    }
}
