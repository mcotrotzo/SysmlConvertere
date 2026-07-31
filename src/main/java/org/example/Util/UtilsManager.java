package org.example.Util;

import lombok.Getter;
import org.omg.sysml.lang.sysml.Element;

public class UtilsManager {

    @Getter
    private static final UtilsManager instance = new UtilsManager();

    private Utils utils;

    private UtilsManager() {}

    public void init(Utils utils) {
        this.utils = utils;
    }

    public Utils getUtils() {
        if (utils == null) {
            throw new IllegalStateException("UtilsManager was not initialized. Call init(Utils utils) before using getUtils().");
        }
        return utils;
    }
}