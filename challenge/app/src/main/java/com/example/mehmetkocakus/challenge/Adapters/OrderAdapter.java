package com.example.mehmetkocakus.challenge.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mehmetkocakus.challenge.Activities.Orders.Model.Order;
import com.example.mehmetkocakus.challenge.Activities.Orders.Model.OrderDetail;
import com.example.mehmetkocakus.challenge.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

//Bu adapter class dıştaki listeyi oluşturur
public class OrderAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<Order> listOfOrder;

    public OrderAdapter(Context context, List<Order> listOfOrder) {
        this.context = context;
        this.listOfOrder = listOfOrder;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return listOfOrder.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listOfOrder.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listOfOrder.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentHolder parentHolder = null;

        Order order = (Order) getGroup(groupPosition);

        if(convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.order_item, null);


            parentHolder = new ParentHolder(convertView);
            convertView.setTag(parentHolder);

        } else {
            parentHolder = (ParentHolder) convertView.getTag();

        }

        parentHolder.marketName.setText(order.getMarketName());
        parentHolder.dateNumber.setText(order.getData());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Integer.valueOf(order.getMonth())-1);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM");
        parentHolder.dateText.setText(simpleDateFormat.format(cal.getTime()));
        parentHolder.orderName.setText(order.getOrderName());
        parentHolder.process.setText(order.getProductState());
        parentHolder.totalPrice.setText(String.format("%.2f",order.getProductPrice())+"TL");

        if(order.getProductState().equals("Yolda"))
        {
            parentHolder.processView.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            parentHolder.process.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }
        else if(order.getProductState().equals("Hazırlanıyor"))
        {
            parentHolder.processView.setBackgroundColor(context.getResources().getColor(R.color.colorOrange));
            parentHolder.process.setTextColor(context.getResources().getColor(R.color.colorOrange));
        }
        else if(order.getProductState().equals("Onay Bekliyor"))
        {
            parentHolder.processView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            parentHolder.process.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder childHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recycler_item, parent, false);
            childHolder = new ChildHolder();
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }


        childHolder.detailRecyclerView = (RecyclerView) convertView.findViewById(R.id.detail_recycler_item);
        childHolder.detailRecyclerView .setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        childHolder.detailRecyclerView.setLayoutManager(layoutManager);


        ArrayList<OrderDetail> listOfDetail=new ArrayList<>();
        listOfDetail.add(listOfOrder.get(groupPosition).getProductDetail());

        OrderItemAdapter recyclerAdapter = new OrderItemAdapter(listOfDetail);
        childHolder.detailRecyclerView.setAdapter(recyclerAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private static class ChildHolder {
        static RecyclerView detailRecyclerView;
    }

   class ParentHolder {

        @BindView(R.id.item_date_number)
        TextView dateNumber;

        @BindView(R.id.item_date_text)
        TextView dateText;

        @BindView(R.id.item_market_name)
        TextView marketName;

        @BindView(R.id.item_order_name)
        TextView orderName;

        @BindView(R.id.item_order_total)
        TextView totalPrice;

        @BindView(R.id.item_process)
        TextView process;

        @BindView(R.id.item_process_view)
        View processView;

       public ParentHolder(View view) {
           ButterKnife.bind(this, view);
       }
    }


}
