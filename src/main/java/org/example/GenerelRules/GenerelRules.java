package org.example.GenerelRules;

import jakarta.inject.Inject;
import org.example.Utils;
import org.omg.sysml.lang.sysml.Element;

public abstract class GenerelRules {


    private final Utils utils = Utils.getInstance();


    public Utils getUtils() {
        return utils;
    }

    public abstract boolean isValid();
}
