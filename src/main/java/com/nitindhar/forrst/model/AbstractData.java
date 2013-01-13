package com.nitindhar.forrst.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class AbstractData {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}