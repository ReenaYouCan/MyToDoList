package com.boot.mytodolist.presenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.boot.mytodolist.R;
import com.boot.mytodolist.intergration_layer.ToDoDatabaseHandler;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ToDoActivity extends AppCompatActivity {

    //Classes Declare classes
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //Inject View for reference in butterknife
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

    }

    /**
     * object initialization of classes
     */
    public void initClasses()
    {
        mDatabaseHandler = new ToDoDatabaseHandler(this);
    }

    public void setAdapter()
    {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
