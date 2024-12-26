package com.caocao.question_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    public String id;

    public String getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public String response;
}
