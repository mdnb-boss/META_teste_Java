package com.marcelo.testes.cadastro.domain.responses;

import java.util.ArrayList;
import java.util.List;

public class OkResponse<T> {

    private boolean ok = false;

    private T data;

    private List<String> errors;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if(this.errors == null){
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}