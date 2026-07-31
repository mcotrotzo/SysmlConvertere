package org.example.GenerelRules;

import org.example.Util.Utils;

public abstract class GenerelRules {


    private final Utils utils = Utils.getInstance();


    public Utils getUtils() {
        return utils;
    }

    public abstract boolean isValid();
}
