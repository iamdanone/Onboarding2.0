package com.playtika.automation.school.test.framework.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GetNoteByIdRequest {

    @JsonProperty("id")
    String id;
    String content;
}