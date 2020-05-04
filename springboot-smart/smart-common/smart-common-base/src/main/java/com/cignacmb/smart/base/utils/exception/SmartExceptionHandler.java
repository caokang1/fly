package com.cignacmb.smart.base.utils.exception;

import com.cignacmb.smart.base.common.ResponseData;
import com.cignacmb.smart.base.common.ReturnFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: caokang
 * @Date: Created in 9:45 2020/4/30
 * @annotation:异常处理器
 * @version:1.0
 */
@ControllerAdvice
@ResponseBody
public class SmartExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(SmartExceptionHandler.class);

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseData runtimeExceptionHandler(RuntimeException runtimeException) {
        LOGGER.error(String.valueOf(runtimeException));
        return ReturnFormat.retParam("1000", "");
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ResponseData nullPointerExceptionHandler(NullPointerException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1001", "");
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResponseData classCastExceptionHandler(ClassCastException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1002", "");
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResponseData ioExceptionHandler(IOException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1003", "");
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseData noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1004", "");
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseData indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1005", "");
    }

    @ExceptionHandler(SQLException.class)
    public ResponseData sqlExcpetionHandler(SQLException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("1007", ex.getCause().getMessage());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseData requestNotReadable(HttpMessageNotReadableException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("400", "");
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseData requestTypeMismatch(TypeMismatchException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("400", "");
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseData requestMissingServletRequest(MissingServletRequestParameterException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("400", "");
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseData request405(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("405", "");
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseData request406(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("406", "未找到路径！");
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ResponseData server500(RuntimeException ex) {
        LOGGER.error(String.valueOf(ex));
        return ReturnFormat.retParam("406", "");
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseData businessException(BusinessException ex) {
        return ReturnFormat.retParam(ex.getStatus(), ex.getError());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseData bindException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();

        StringBuilder errorMessage = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {

            if (errorMessage.length() == 0) {
                errorMessage.append(fieldError.getDefaultMessage());
            } else {
                errorMessage.append(",").append(fieldError.getDefaultMessage());
            }

        }

        System.out.println(errorMessage.toString());

        return ReturnFormat.retParam(ReturnFormat.RESPONSE_FAIL_CODE_9999, errorMessage.toString());
    }

}
