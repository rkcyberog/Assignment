package com.example.rahul.zylaassignment.adapter;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rahul.zylaassignment.MainActivity;
import com.example.rahul.zylaassignment.Model.Child;
import com.example.rahul.zylaassignment.Model.ParentChild;
import com.example.rahul.zylaassignment.databinding.ParentBinding;

import java.util.ArrayList;


public class ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ParentChild> parentChildData;
    Context ctx;
    private LayoutInflater inflater;

    public ParentAdapter(Context ctx, ArrayList<ParentChild> parentChildData) {
        this.ctx = ctx;
        this.parentChildData = parentChildData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ParentBinding parentBinding;

        public ViewHolder(ParentBinding dataBinding) {
            super(dataBinding.getRoot());
            this.parentBinding = dataBinding;
        }

        public void bind(ParentChild parentChild) {
            this.parentBinding.setViewModel(parentChild);
        }

        public ParentBinding getParentBinding() {
            return parentBinding;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ParentBinding parentBinding = ParentBinding.inflate(inflater, parent, false);
        return new ViewHolder(parentBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        final ParentChild model = parentChildData.get(position);
        vh.bind(model);

        //Getting child recycleview and passing for setting GridLayout
        initChildLayoutManager(vh.getParentBinding().recycleViewSong, model.getChild(), MainActivity.columnNum);
    }

    private void initChildLayoutManager(RecyclerView rvChild, ArrayList<Child> childData, int columnNum) {

        //Checking for column spacing in GridLayout of Child
        if (childData.size() < columnNum) {
            rvChild.setLayoutManager(new GridLayoutManager(ctx, childData.size(), GridLayoutManager.HORIZONTAL, false));
        } else {
            rvChild.setLayoutManager(new GridLayoutManager(ctx, columnNum, GridLayoutManager.HORIZONTAL, false));
        }
        ChildAdapter childAdapter = new ChildAdapter(childData);
        rvChild.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return parentChildData.size();
    }
}
