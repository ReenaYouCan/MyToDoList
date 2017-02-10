package com.boot.mytodolist.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.boot.mytodolist.R;
import com.boot.mytodolist.interfaces.CallBackInterface;
import com.boot.mytodolist.intergration_layer.ToDoDatabaseHandler;
import com.boot.mytodolist.model.ToDoModel;
import com.boot.mytodolist.utils.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activ.ities that contain this fragment must implement the
 * {@link CallBackInterface} interface
 * to handle interaction events.
 * Use the {@link ToDoAddDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoAddDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    //Views binding
    @InjectView(R.id.edtDescription)
    EditText edtDescription;

    @InjectView(R.id.spPriority)
    Spinner spPriority;

    @InjectView(R.id.btnDone)
    Button btnDone;

    @InjectView(R.id.container)
    FrameLayout containerLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ToDoDatabaseHandler mDatabaseHandler;
    private CallBackInterface mCallBackInterface;

    public ToDoAddDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDoAddDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDoAddDialogFragment newInstance(String param1, String param2) {
        ToDoAddDialogFragment fragment = new ToDoAddDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_to_do_add_dialog, container,
                false);
        //injecting view
        ButterKnife.inject(this, rootView);
        getDialog().setTitle("Add ToDo");
        addPriorityToSpinner();

        return rootView;
    }

    @OnClick(R.id.btnDone)
    public void ToDoTodatabase()
    {
        //Empty Validation
        if(edtDescription.getText().toString().equalsIgnoreCase("")) {
            CommonUtility.sharedInstance().showSnackbar(containerLayout, getActivity().getResources().getString(R.string.err_description));
            edtDescription.requestFocus();
            return;
        }
        //Add data to model
        ToDoModel model = new ToDoModel();
        model.setTodoText(edtDescription.getText().toString());
        model.setPriority(Integer.parseInt(spPriority.getSelectedItem().toString()));
        //Add data to integration layer
        mCallBackInterface.onAdd(model);
        //close dialog
        this.dismiss();
    }


    /**
     * Add Priority list and Adapter-Datasource to Spinner
     */
    public void addPriorityToSpinner() {
        // Spinner Drop down elements
        List<Integer> categories = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            categories.add(i);
        }
        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, categories);
        // Drop down layout style -
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spPriority.setAdapter(dataAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallBackInterface) {
            mCallBackInterface = (CallBackInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallBackInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBackInterface = null;
    }
}
