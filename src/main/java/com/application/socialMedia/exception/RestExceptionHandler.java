package com.application.socialMedia.exception;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

        // @ExceptionHandler(value = { Exception.class })
        // public ResponseEntity<?> handleException(Exception e) {
        //     return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        // }

        // @ExceptionHandler(value = { RuntimeException.class })
        // public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        //     return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        // }

        @ExceptionHandler(value = {RuntimeException.class})
        public ResponseEntity<?> runTimeException(RuntimeException e)
        {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
        @ExceptionHandler(value = { IllegalArgumentException.class })
        public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
        }

        @ExceptionHandler(value = { IllegalStateException.class })  
        public ResponseEntity<?> handleIllegalStateException(IllegalStateException e) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
        }

        @ExceptionHandler(value = { SecurityException.class })
        public ResponseEntity<?> handleSecurityException(SecurityException e) {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).body(e.getMessage());
        }
        
        @ExceptionHandler(value = {UsernameNotFoundException.class})
        public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e)
        {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).body(e.getMessage());
        }
        @ExceptionHandler(value = {AccessDeniedException.class})
        public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e)
        {
            return ResponseEntity.status(Response.SC_FORBIDDEN).body(e.getMessage());
        }
        
}
