package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobDetailFragment extends Fragment {

    private static final String Job = "job";

    private Job job;

    public JobDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobDetailFragment newInstance(Job job) {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Job, job);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarId);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });*/

        if (getArguments() != null) {
            job = (Job) getArguments().getSerializable(Job);

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setJob(view,job);

    }


    public void setJob(View view, Job job) {

        this.job = job;

        TextView txtJobName = (TextView)view.findViewById(R.id.txtJobName);
        txtJobName.setText(job.getJobName());

        TextView txtJobDesc = (TextView)view.findViewById(R.id.txtDescription);
        txtJobDesc.setText(job.getJobDescription());
        txtJobDesc.setEnabled(false);


    }

}