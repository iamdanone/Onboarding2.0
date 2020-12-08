package com.playtika.automation.school.test.framework.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateNoteRequest {
    @JsonProperty("content")
    String content;
    @JsonProperty("version")
    String version;
}
