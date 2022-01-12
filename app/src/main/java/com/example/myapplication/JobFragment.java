package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */

//All Job Fragment implementations belogns to Ali Marangoz
public class JobFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private JobListener jobListener;
    List<Job>jobs = new ArrayList<>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JobFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static JobFragment newInstance(int columnCount) {
        JobFragment fragment = new JobFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }



        jobs.add(new Job("Web Design","Web Design is required HTML, CSS, Js, React, Angular " +
                "knowledge and also it requires complete the job on time."));
        jobs.add(new Job("Private Lesson","Private Lessons is required graduation degree for every lesson, " +
                "it is possible to give lesson without any degree but He/She should communicate with customer face to face. "));
        jobs.add(new Job("Domestic Work","Domestic work requirement is phone call with the customer. " +
                "Job can describe as simple cleaning stuffs, repairing is available in Domestic Work. Privacy is important for this job. "));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyJobRecyclerViewAdapter(jobs,jobListener));
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof JobListener){
            jobListener = (JobListener)context;
        }else{
            throw new RuntimeException(context.toString() + "must implement Job Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        jobListener = null;
    }

    public interface JobListener{
        void JobSelected(Job job);
    }
}