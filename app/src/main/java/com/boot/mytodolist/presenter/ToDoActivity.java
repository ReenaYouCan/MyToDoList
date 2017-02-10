package com.boot.mytodolist.presenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.boot.mytodolist.R;
import com.boot.mytodolist.interfaces.CallBackInterface;
import com.boot.mytodolist.intergration_layer.ToDoDatabaseHandler;
import com.boot.mytodolist.model.ToDoModel;
import com.boot.mytodolist.view.adapter.ToDoDataAdapter;
import com.boot.mytodolist.view.fragments.ToDoAddDialogFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ToDoActivity extends AppCompatActivity implements CallBackInterface {

    //Declaration of classes
    private ToDoDatabaseHandler mDatabaseHandler;

    //View Binding
    @InjectView(R.id.btnAddNewTodo)
    FloatingActionButton btnAddNewTodo;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.tvEmptyView)
    TextView tvEmptyView;

    @InjectView(R.id.rvToDoList)
    RecyclerView rvToDoList;

    //Declaration of arraylist
    private ArrayList<ToDoModel> mArrToDoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //Inject View for reference in butterknife
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        initClasses();
        setLayoutManager();
        setDataSource();
    }

    /**
     * object initialization for classes
     */
    public void initClasses()
    {
        mDatabaseHandler = new ToDoDatabaseHandler(this);
    }


    /**
     * Set Linear Layout Manager to RecyclerView
     */
    public void setLayoutManager()
    {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvToDoList.setLayoutManager(mLayoutManager);
    }

    /**
     * Set Datasource to Recycler view
     */
    public void setDataSource()
    {
        mArrToDoList = mDatabaseHandler.getAllDoTo();
        if(mArrToDoList!=null&&mArrToDoList.size()>0) {
            //Sort List based on priority- 1 is the highest priority
            Collections.sort(mArrToDoList, new Comparator<ToDoModel>() {
                public int compare(ToDoModel p1, ToDoModel p2) {
                    return Integer.valueOf(p1.getPriority()).compareTo(p2.getPriority());
                }
            });
            rvToDoList.setAdapter(new ToDoDataAdapter(mArrToDoList,this));
            rvToDoList.setVisibility(View.VISIBLE);                 // Handle Visibility based on items in datasource
            tvEmptyView.setVisibility(View.GONE);
        }else {
            tvEmptyView.setVisibility(View.VISIBLE);
            rvToDoList.setVisibility(View.GONE);
        }
    }

    /**
     * Show AddToDoDialog based on floating button click
     */
    @OnClick(R.id.btnAddNewTodo)
    public void showAddToDoDialog()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ToDoAddDialogFragment fragment = new ToDoAddDialogFragment();
        fragment.show(fragmentManager,"ToDoAddDialogFragment");
    }


    /**
     * Callback to delete todo_item
     * @param pos
     */
    @Override
    public void onDelete(int pos) {
        mDatabaseHandler.deleteTodo(mArrToDoList.get(pos));
        setDataSource();
    }

    /**
     * Callback to add new todo_item
     * @param model
     */
    @Override
    public void onAdd(ToDoModel model) {
        mDatabaseHandler.addTodo(model);
        setDataSource();
    }
}
