package com.boot.mytodolist.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.boot.mytodolist.R;
import com.boot.mytodolist.interfaces.CallBackInterface;
import com.boot.mytodolist.model.ToDoModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by Reena on 2/9/2017.
 */

public class ToDoDataAdapter extends RecyclerView.Adapter<ToDoDataAdapter.ViewHolder> {

    private ArrayList<ToDoModel> mArrToDoList;
    private Context mContext;

    private CallBackInterface mCallBackInterface;
    /**
     * parameterized constructor to pass initial value
     * @param arrToDoList
     * @param context
     */
    public ToDoDataAdapter(ArrayList<ToDoModel> arrToDoList,Context context)
    {
        this.mArrToDoList = arrToDoList;
        this.mContext= context;
        this.mCallBackInterface = (CallBackInterface)mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String description = "<b>" + mContext.getResources().getString(R.string.text_description) + "</b> <br>\n" + mArrToDoList.get(position).getTodoText();
        holder.tvDescription.setText(Html.fromHtml(description));
        String priority = "<b>" + mContext.getResources().getString(R.string.text_priority) + "</b> <br>" + mArrToDoList.get(position).getPriority();
        holder.tvPriority.setText(Html.fromHtml(priority));
        holder.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBackInterface.onDelete(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrToDoList.size();
    }

    /**
     * Viewholder class to achieve recycling
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.tvDescription)
        TextView tvDescription;

        @InjectView(R.id.tvPriority)
        TextView tvPriority;

        @InjectView(R.id.btnDone)
        ImageButton btnDone;

        /**
         * Constructor with View
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }
 }
