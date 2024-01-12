package com.test.book.commands;

import com.test.book.utils.StringUtils;

public class KeyValue {

    public String getCommand() {
        return StringUtils.unicodeEncode(command);
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private String command;

    public String getDescription() {
        return StringUtils.unicodeEncode(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public KeyValue(String key, String value) {
        this.command =key;
        this.description = value;
    }

}

