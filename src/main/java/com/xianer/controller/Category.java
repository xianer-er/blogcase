package com.xianer.controller;

public class Category {
    private  int idcategory;
    private String categorycol;

    public Category(){

    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategorycol() {
        return categorycol;
    }

    public void setCategorycol(String categorycol) {
        this.categorycol = categorycol;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idcategory=" + idcategory +
                ", categorycol='" + categorycol + '\'' +
                '}';
    }
}
