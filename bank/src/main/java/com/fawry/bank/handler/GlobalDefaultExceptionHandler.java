package com.fawry.bank.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
class GlobalDefaultExceptionHandler {
    //public static final String DEFAULT_ERROR_VIEW = "error";

   /* @ExceptionHandler(value = Exception.class)
    public ModelAndView
     defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;


        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        System.out.println(e.getMessage()+"\n"+req.getRequestURL());
        return mav;
    }*/

  /* @ExceptionHandler(value = Exception.class)
   public ResponseEntity<ErrorResponse> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
       if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
           throw e;
       }

       ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), req.getRequestURL().toString());
       return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @Data
    static class ErrorResponse {
        private String message;
        private String url;

        public ErrorResponse(String message, String url) {
            this.message = message;
            this.url = url;
        }

        // Getters and setters
    }*/
}