package com.simpletech.MartyrMemory.model;

/**
 * @author george zeng
 * @module
 * @date 23:22
 */
public class Bean {
    private Message message;
    private Visitor visitor;

    public Bean() {
    }

    public Bean(Message message, Visitor visitor) {
        this.message = message;
        this.visitor = visitor;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "message=" + message +
                ", visitor=" + visitor +
                '}';
    }
}
