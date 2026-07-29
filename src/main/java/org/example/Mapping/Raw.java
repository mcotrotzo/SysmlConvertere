package org.example.Mapping;

import jakarta.inject.Inject;
import lombok.Getter;
import lombok.ToString;
import org.example.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.TypeUtil;

import java.util.Set;

@ToString(
        includeFieldNames = true,
        of = {"sysmlElementName"})
public abstract class Raw<T extends Type> {
    @Getter
    private final T sysmlElement;

    @Getter
    private final String sysmlElementName;


    @Getter
    private Utils utils = Utils.getInstance();

    public Raw(T sysmlElement) {
        this.sysmlElement = sysmlElement;
        this.sysmlElementName = sysmlElement.getName();
    }

    protected final RawRegistry rawRegistry = RawRegistry.getInstance();

}