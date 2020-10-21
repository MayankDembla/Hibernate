package org.dembla.validator;

import javax.validation.constraints.NotNull;

public class MyChildBean {

    private String data;

    @NotNull
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
