package ru.sanikitin.spark.DTO;

import java.io.Serializable;
import java.util.Date;

public class AggregatedLogDTO implements Serializable {
    private Date datetime;
    private String type;
    private Integer count;

    /**
     * Default constructor
     * @return Aggregated log
     */
    public AggregatedLogDTO(){

    }
    /**
     * Constructor with arguments
     * @param datetime Datetime hour
     * @param type Type of message (error, warning, etc)
     * @param count count of messages with this type and this hour
     * @return Aggregated log
     */
    public AggregatedLogDTO(Date datetime, String type, Integer count){
        this.datetime = datetime;
        this.type = type;
        this.count = count;
    }

    /**
     * Returns datetime hour (example 22.10.2020 01:00:00)
     * @return
     */
    public Date getDatetime(){
        return datetime;
    }

    /**
     * Set datetime
     * @param dttm datetime
     */
    public void setDatetime(Date dttm){
        datetime = dttm;
    }

    /**
     * Returns type of messages
     * @return
     */
    public String getType(){
        return type;
    }

    /**
     * Set type of messages
     * @param type type
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Returns count of messages
     * @return
     */
    public Integer getCount(){
        return count;
    }

    /**
     * Set count of messages
     * @param count
     */
    public void setCount(Integer count){
        this.count = count;
    }
}
