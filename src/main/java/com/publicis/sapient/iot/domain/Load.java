package com.publicis.sapient.iot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Load {

    private LocalDateTime timeStamp;

    private long load;

    public Load() {

    }

    public Load(LocalDateTime timeStamp, long load) {
        this.timeStamp = timeStamp;
        this.load = load;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getLoad() {
        return load;
    }

    public void setLoad(long load) {
        this.load = load;
    }
}
