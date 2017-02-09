package com.boot.mytodolist.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.boot.mytodolist.R;
import com.boot.mytodolist.model.ToDoModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Reena on 2/9/2017.
 */

public class ToDoDataAdapter extends RecyclerView.Adapter<ToDoDataAdapter.ViewHolder> {

    private ArrayList<ToDoModel> mArrToDoList;
    private Context mContext;

    /**
     * parameterized constructor to pass initial value
     * @param arrToDoList
     * @param context
     */
    public ToDoDataAdapter(ArrayList<ToDoModel> arrToDoList,Context context)
    {
        this.mArrToDoList = arrToDoList;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mArrToDoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.tvDescription)
        TextView tvDescription;

        @InjectView(R.id.tvPriority)
        TextView tvPriority;

        @InjectView(R.id.btnDone)
        Button btnDone;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }
}
