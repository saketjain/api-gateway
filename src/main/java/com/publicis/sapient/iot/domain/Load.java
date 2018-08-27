package com.publicis.sapient.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Load {

    private long timeStamp;

    private long load;

    public Load() {

    }

    public Load(long timeStamp, long load) {
        this.timeStamp = timeStamp;
        this.load = load;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getLoad() {
        return load;
    }

    public void setLoad(long load) {
        this.load = load;
    }
}
