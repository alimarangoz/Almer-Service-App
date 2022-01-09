package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.myapplication.databinding.FragmentJobBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyJobRecyclerViewAdapter extends RecyclerView.Adapter<MyJobRecyclerViewAdapter.ViewHolder> {

    private final List<Job> mValues;
    private final JobFragment.JobListener jobListener;
    int selectedIndex = -1;

    public MyJobRecyclerViewAdapter(List<Job> mValues, JobFragment.JobListener jobListener) {
        this.mValues = mValues;
        this.jobListener = jobListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentJobBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if(position+1 % 3 == 1){
            holder.mJobImgView.setImageResource(R.mipmap.ic_web_design_foreground);
        }else if(position+1 % 3 == 2){
            holder.mJobImgView.setImageResource(R.mipmap.ic_private_lesson_foreground);
        }else{
            holder.mJobImgView.setImageResource(R.mipmap.ic_domestic_foreground);
        }

        holder.mContentView.setText(mValues.get(position).getJobName());
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jobListener != null){
                    jobListener.JobSelected(holder.mItem);
                    notifyItemChanged(selectedIndex);
                    selectedIndex = holder.getLayoutPosition();
                    notifyItemChanged(selectedIndex);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mJobImgView;
        public final TextView mContentView;
        public Job mItem;

        public ViewHolder(FragmentJobBinding binding) {
            super(binding.getRoot());
            mJobImgView = binding.jobImgView;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}