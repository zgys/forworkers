package com.diting.zgy.forworkers.events;

/**
 * Created by Administrator on 2015/8/15.
 */
public class EventStuffRestOrWork {

    private int stuffStatusCode;

    public EventStuffRestOrWork(int stuffStatusCode){
        this.stuffStatusCode = stuffStatusCode;
    }

    public int getStuffStatusCode() {
        return stuffStatusCode;
    }
}
