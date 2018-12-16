package com.stackstate.mm;

public class Reply {

    private final long id;
    private final String source;

    public Reply(long id, String source) {
        this.id = id;
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }
}
