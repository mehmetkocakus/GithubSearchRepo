package com.example.mehmetkocakus.challenge.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mehmetkocakus.challenge.Activities.Orders.Model.OrderDetail;
import com.example.mehmetkocakus.challenge.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

//Bu adapter class içteki listeyi oluşturur.
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private List<OrderDetail> item;

    public OrderItemAdapter(List<OrderDetail> item) {
        this.item = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_inner_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.orderName.setText(item.get(position).getOrderDetail());
            holder.price.setText(String.format("%.2f",item.get(position).getSummaryPrice())+"TL");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_order_name)
        TextView orderName;

        @BindView(R.id.item_order_total)
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
