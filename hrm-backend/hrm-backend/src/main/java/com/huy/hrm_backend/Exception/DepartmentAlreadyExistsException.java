package com.huy.hrm_backend.Exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
    public DepartmentAlreadyExistsException( String message){
        super(message);
    }
}
