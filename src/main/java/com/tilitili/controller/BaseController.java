package com.tilitili.controller;

import com.tilitili.common.entity.view.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseModel handleError(HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);
        return new BaseModel(ex.getMessage());
    }
}
