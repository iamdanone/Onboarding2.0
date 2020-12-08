package com.playtika.automation.school.test.framework.pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CreateNoteResponse {
    @JsonProperty("id")
    String id;
    @JsonProperty("content")
    String content;
    @JsonProperty("version")
    String version;
}