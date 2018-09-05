package com.example.rahul.zylaassignment.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.rahul.zylaassignment.Model.Child;
import com.example.rahul.zylaassignment.databinding.ChildBinding;
import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ChildBinding childBinding;
    private ArrayList<Child> childData;
    private LayoutInflater inflater;
    public ChildAdapter(ArrayList<Child> childData) {
        this.childData = childData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ChildBinding childBinding;
        public ViewHolder(ChildBinding dataBinding) {
            super(dataBinding.getRoot());
            this.childBinding = dataBinding;
        }

        public void bind(Child child){
            this.childBinding.setViewModel(child);
        }

        public ChildBinding getChildBinding() {
            return childBinding;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(inflater == null){
            inflater =LayoutInflater.from(parent.getContext());
        }
        childBinding = ChildBinding.inflate(inflater, parent,false);

        return new ViewHolder(childBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
        final Child child = childData.get(position);
        vh.bind(child);
    }

    @Override
    public int getItemCount() {
        return childData.size();
    }
}
