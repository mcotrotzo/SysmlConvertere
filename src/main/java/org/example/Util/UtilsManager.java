package org.example.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.omg.sysml.lang.sysml.Element;

@Getter
public class UtilsManager {

    @Getter
    private final static UtilsManager instance = new UtilsManager();
    private Utils utils;

    private UtilsManager(){}

    public void init(Utils utils) {
        this.utils = utils;
    }



}
