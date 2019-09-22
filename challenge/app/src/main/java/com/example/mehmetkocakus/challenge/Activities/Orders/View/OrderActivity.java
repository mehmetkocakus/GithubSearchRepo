package com.example.mehmetkocakus.challenge.Activities.Orders.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.mehmetkocakus.challenge.Activities.Login.Interfaces.ILoginContract;
import com.example.mehmetkocakus.challenge.Activities.Login.Presenter.LoginPresenters;
import com.example.mehmetkocakus.challenge.Activities.Login.View.LoginActivity;
import com.example.mehmetkocakus.challenge.Activities.Orders.Interfaces.IOrderConract;
import com.example.mehmetkocakus.challenge.Activities.Orders.Model.Order;
import com.example.mehmetkocakus.challenge.Activities.Orders.Presenter.OrderPresenter;
import com.example.mehmetkocakus.challenge.Adapters.OrderAdapter;
import com.example.mehmetkocakus.challenge.Classes.Tools;
import com.example.mehmetkocakus.challenge.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements IOrderConract.UserInterface,ILoginContract.IUserInterface {

    @BindView(R.id.order_list)
    ExpandableListView orderList;

    @BindView(R.id.order_order_button)
    Button orderButton;

    @BindView(R.id.order_logout_button)
    Button orderLogoutButton;

    @BindView(R.id.order_list_error_message)
    TextView listErrorMessage;

    OrderPresenter orderPresenter;
    LoginPresenters loginPresenters;
    OrderAdapter orderAdapter;
    List<Order> listOfOrder;
    Tools tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        ButterKnife.bind(this);
        tool=new Tools();
        listOfOrder=new ArrayList<>();
        loginPresenters=new LoginPresenters(OrderActivity.this);
        orderPresenter=new OrderPresenter(OrderActivity.this);
        orderPresenter.getOrderList();

        orderAdapter=new OrderAdapter(OrderActivity.this,listOfOrder);
        orderList.setAdapter(orderAdapter);

        orderList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if ((previousGroup != -1) && (groupPosition != previousGroup)) {
                    orderList.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });
    }

    @Optional
    @OnClick({R.id.order_order_button,R.id.order_logout_button})
    public void onClick(View v) {
        tool.preventTwoClick(v);
        switch (v.getId()) {
            case R.id.order_order_button:
                orderPresenter.getOrderList();
                break;
            case R.id.order_logout_button:
                showExitDialog();
                break;
        }
    }

    /**
     * Bu metot çıkış yapma diyaloğunu açar
     */
    public void showExitDialog() {
        loginPresenters=new LoginPresenters(OrderActivity.this);
        final MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(OrderActivity.this)
                .withDialogAnimation(true)
                .setDescription(R.string.are_you_sure_you_want_to_leave_application_string)
                .setHeaderColor(R.color.colorMaterialHeader)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        loginPresenters.deleteUser();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .setPositiveText(R.string.logout_string)
                .setNegativeText(R.string.cancel_string)
                .setStyle(Style.HEADER_WITH_ICON)
                .setIcon(R.drawable.exit_24dp)
                .build();

        dialog.show();
    }

    //Bu metot web servisden gelen verileri listeye ekler
    @Override
    public void resultListOrder(List<Order> listOfResult) {
        listOfOrder.clear();
        if(listOfResult!=null && listOfResult.size()>0)
        {
            orderList.setVisibility(View.VISIBLE);
            listErrorMessage.setVisibility(View.INVISIBLE);
            listOfOrder.addAll(listOfResult);
            if(orderAdapter!=null) {
                orderAdapter.notifyDataSetChanged();
            }
        }
        else
        {
            listErrorMessage.setVisibility(View.VISIBLE);
            orderList.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void resultLogin(boolean isLogin, int type) {

    }

    //çıkış yapan kullanıcının bilgileri veritabanından silindikten sonra loginactivity açılır
    @Override
    public void resultDelete(Activity activity) {
        Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }
}
