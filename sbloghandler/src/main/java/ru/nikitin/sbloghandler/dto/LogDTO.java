package ru.nikitin.sbloghandler.dto;

import java.util.Date;
import java.util.Objects;

public class LogDTO {
    private long    id;
    private Date    datetime;
    private String  hostname;
    private String  process;
    private String  message;
    private Integer priority;

    /**
     * Default constructor
     * @return Metric
     */
    public LogDTO() {
    }

    /**
     * LogDTO constructor
     * @param dttm Date and tim of row
     * @param hostname hostname
     * @param process process name
     * @param message message text
     * @param priority priority of message
     * @return Row-DTO object
     */
    public LogDTO(long id, Date dttm, String hostname, String process, String message, Integer priority) {
        this.id = id;
        this.datetime = dttm;
        this.hostname = hostname;
        this.process = process;
        this.message = message;
        this.priority = priority;
    }

    /**
     * Return unique ID
     * @return id
     */
    public long getId(){ return id; }

    /**
     * Set unique ID
     * @param id
     */
    public void setId(long id){ this.id = id; }
    /**
     * Return date and time of row
     * @return Datetime
     */
    public Date getDatetime(){
        return datetime;
    }

    /**
     * Set date and time
     * @param dttm
     */
    public void setDatetime(Date dttm){
        datetime = dttm;
    }

    /**
     * Return hostname
     * @return String of hostname
     */
    public String getHostname(){
        return hostname;
    }

    /**
     * Set hostname
     * @param hostname
     */
    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    /**
     * Return message text of row
     * @return String of message text
     */
    public String getMessage(){
        return message;
    }

    /**
     * Set message text of row
     * @param msgtext
     */
    public void setMessage(String msgtext){
        message = msgtext;
    }

    /**
     * Return priority of row
     * @return Priority (int)
     */
    public Integer getPriority(){
        return priority;
    }

    /**
     * Set priority of row
     * @param priority
     */
    public void setPriority(Integer priority){
        this.priority = priority;
    }
    /**
     * Return process
     * @return Process name (string)
     */
    public String getProcess(){
        return process;
    }

    /**
     * Set process of row
     * @param process
     */
    public void setProcess(String process){
        this.process = process;
    }
    /**
     * Returns a string representation of the object
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("(%d, %s, %s, %s, %s)", priority, datetime.toString(), hostname, process, message);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param dst Object compare with
     * @return Equals flag
     */
    @Override
    public boolean equals(Object dst) {
        if (this == dst) {
            return true;
        }
        else if (dst == null || getClass() != dst.getClass()){
            return false;
        }
        else {
            LogDTO dstDto = (LogDTO) dst;
            return  datetime.equals(dstDto.datetime) &&
                    hostname.equals(dstDto.hostname) &&
                    process.equals(dstDto.process) &&
                    message.equals(dstDto.message) &&
                    priority.equals(dstDto.priority);
        }

    }

    /**
     * Returns a hash code value for the object.
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(datetime, hostname, process, message, priority);
    }
}