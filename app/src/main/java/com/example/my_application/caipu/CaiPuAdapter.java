package com.example.my_application.caipu;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bh.ldp.lib_base.view.BaseRecyclerViewAdapter;
import com.bh.ldp.lib_base.view.RecyclerViewHolder;
import com.example.my_application.R;
import com.example.my_application.details.CaiDetailsActivity;

import java.util.List;

import bean.CaiDetailsBean;

/**
 * created by Da Peng at 2019/10/12
 */
public class CaiPuAdapter extends BaseRecyclerViewAdapter<CaiDetailsBean> {


    public CaiPuAdapter(Context context, List<CaiDetailsBean> list) {
        super(context, list);
    }

    @Override
    protected int getItemLayoutResId() {
        return R.layout.item_cai_pu;
    }

    @Override
    protected void bindItemViewHolder(RecyclerViewHolder holder, int position, final CaiDetailsBean item) {

        holder.setImagineByNetUrl(content, R.id.cai_iv, item.getPic())
                .setText(R.id.cai_name, item.getName())
                .setText(R.id.cai_tag, item.getTag());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.startActivity(new Intent(content, CaiDetailsActivity.class)
                        .putExtra("details", item));
            }
        });

    }

}


class Person {

    private static Person person;

    private Person() {
    }

    public static Person getInstance() {

        if (person == null) {

            synchronized (Person.class) {
                if (person == null) {
                    person = new Person();
                }
            }

        }

        return person;
    }

    public static Person getInstance2() {
        return Instance.sInstance;
    }

    public static Person getInstance3() {
        return InsTanceEnum.INSTANCE.getPerson();
    }

    private static class Instance {
        private static final Person sInstance = new Person();
    }

    private enum InsTanceEnum {
        INSTANCE;

        private Person person;

        InsTanceEnum() {
            person = new Person();
        }

        public Person getPerson() {
            return person;
        }

    }
}