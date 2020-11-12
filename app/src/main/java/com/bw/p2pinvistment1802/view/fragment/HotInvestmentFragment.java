package com.bw.p2pinvistment1802.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bw.lib_core.view.BaseFragment;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.contract.InvestmentContract;
import com.bw.p2pinvistment1802.model.InvestmentModel;
import com.bw.p2pinvistment1802.presenter.InvestmentPresenter;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;

import java.util.ArrayList;
import java.util.List;

public class HotInvestmentFragment extends BaseFragment<InvestmentPresenter> implements InvestmentContract.View {

    private AutoFlowLayout autoFlowLayout;
    private int[] colors = {0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    private List<String > list = new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.fragment_hot_investment;
    }

    @Override
    public void initView() {

        autoFlowLayout = (AutoFlowLayout) findViewById(R.id.autoFlowLayout);
    }

    @Override
    public void initInject() {

        mPresenter = new InvestmentPresenter(new InvestmentModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_investment();
    }

    @Override
    public void initAdapter(AllInvestmentBean allInvestmentBean) {

        List<AllInvestmentBean.ResultBean> result = allInvestmentBean.getResult();
        list.clear();
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i).getName());
        }

        autoFlowLayout.setAdapter(new FlowAdapter(list) {
            @Override
            public View getView(int i) {
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_hot_item, null);
                TextView textView = inflate.findViewById(R.id.auto_tv);
                int newcolor = colors[(int)(colors.length * Math.random())];
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(30);
                gradientDrawable.setColor(newcolor);
                textView.setBackground(gradientDrawable);
                textView.setText(list.get(i));

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gradientDrawable.setCornerRadius(30);
                        gradientDrawable.setColor(Color.WHITE);
                        Toast.makeText(getContext(), ""+list.get(i), Toast.LENGTH_SHORT).show();
                    }
                });
                return inflate;
            }
        });

    }
}
