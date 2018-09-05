package com.example.rahul.zylaassignment.Model;

import java.util.ArrayList;

public class ParentChild {
    ArrayList<Child> child;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    String categoryName;

    public ParentChild() {}

    public ArrayList<Child> getChild() {
        return child;
    }

    public void setChild(ArrayList<Child> child) {
        this.child = child;
    }
}
