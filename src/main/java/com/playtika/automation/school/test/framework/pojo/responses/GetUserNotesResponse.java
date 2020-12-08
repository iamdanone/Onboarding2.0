package com.playtika.automation.school.test.framework.pojo.responses;

import java.util.Date;
import lombok.Value;

@Value
public class GetUserNotesResponse {

    public int id;
    public String content;
    public Date createdAt;
    public Date modifiedAt;
    public int version;
}