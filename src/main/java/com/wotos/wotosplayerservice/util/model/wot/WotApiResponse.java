package com.wotos.wotosplayerservice.util.model.wot;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public class WotApiResponse<T> {

    private final Object status;
    private final Object error;
    private final Object meta;
    private final T data;

    public WotApiResponse(String status, Object error, Object meta, T data) {
        this.status = status;
        this.error = error;
        this.meta = meta;
        this.data = data;
    }

    public Object getStatus() {
        return status;
    }

    public Object getError() {
        return error;
    }

    public Object getMeta() {
        return meta;
    }

    @Nullable
    public T getData() {
        return data;
    }

}
