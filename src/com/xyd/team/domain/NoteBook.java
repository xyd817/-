package com.xyd.team.domain;

/**
 * @author xyd
 * @create 2022-06-26 19:39
 */
public class NoteBook implements Equipment{
    private String model;//机器型号
    private String price;//价格

    public NoteBook() {
    }

    public NoteBook(String price) {
        this.price = price;
    }

    public NoteBook(String model, String price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model + "(" + price + ")";
    }
}
