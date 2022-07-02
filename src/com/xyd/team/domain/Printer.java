package com.xyd.team.domain;

/**
 * @author xyd
 * @create 2022-06-26 19:42
 */
public class Printer implements Equipment{
    private String name;
    private String type;

    public Printer() {
    }

    public Printer(String name) {
        this.name = name;
    }

    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return name + "(" + type + ")";
    }
}
