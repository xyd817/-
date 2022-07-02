package com.xyd.team.domain;


/**
 * @author xyd
 * @create 2022-06-26 19:48
 */
public class Status {
    private final String NAME;

    public Status(String NAME) {
        this.NAME = NAME;
    }
    public static final Status BUSY = new Status("BUSY");
    public static final Status FREE = new Status("FREE");
    public static final Status VOCATION = new Status("VOCATION");

    @Override
    public String toString(){
        return NAME;
    }

}
