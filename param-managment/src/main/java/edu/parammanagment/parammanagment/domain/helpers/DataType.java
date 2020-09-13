package edu.parammanagment.parammanagment.domain.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DataType {

    LONG,
    INTEGER,
    FLOAT,
    BIG_DECIMAL,
    STRING,
    DATE,
    TIME,
    DATETIME;

    @JsonCreator
    public static DataType forValue(String value){
        return DataType.valueOf(value);
    }
}