package ru.nikitin.sbloghandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * POJO входных данных (логов)*/
@Data
@AllArgsConstructor
public class LogDTO implements Serializable {
    private Long id;
    private DateTime datetime;
    private String hostname;
    private String process;
    private String message;
    private Integer priority;
}