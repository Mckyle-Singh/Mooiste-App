package vcpe.st10118615.mooiste.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.model.Order;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> orderArrayList;
    private Order order;

    public OrdersAdapter(Context context, ArrayList<Order> orderArrayList) {
        this.context = context;
        this.orderArrayList = orderArrayList;
        this.order = order;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Order order = orderArrayList.get(position);
        holder.status.setText(order.getStatus());
        if(order.getStatus().equals("Processed")){
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
        }
        holder.date.setText(order.getDateOfOrder());
        holder.totalPrice.setText("R"+order.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status,date,totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.order_status);
            date = itemView.findViewById(R.id.date_of_order);
            totalPrice = itemView.findViewById(R.id.order_total_price);
        }
    }
}
