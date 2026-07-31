package org.example.Mapping;

import lombok.Getter;
import lombok.ToString;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;

@ToString(
        includeFieldNames = true,
        of = {"sysmlElementName"})
public abstract class Raw {
    @Getter
    private final Type sysmlElement;

    @Getter
    private final String sysmlElementName;


    @Getter
    private Utils utils = Utils.getInstance();

    public Raw(Type sysmlElement) {
        this.sysmlElement = sysmlElement;
        this.sysmlElementName = sysmlElement.getName();
    }

    protected final RawRegistry rawRegistry = RawRegistry.getInstance();

}