package com.nitindhar.forrst.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
public abstract class AbstractData implements Serializable {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}