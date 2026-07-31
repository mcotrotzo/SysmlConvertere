package org.example.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.omg.sysml.lang.sysml.Element;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
public class RessourceSetUtils {
    private ResourceSet resourceSet;

    public RessourceSetUtils(Element rootElement) {
        this.resourceSet = rootElement.eResource().getResourceSet();
    }
}
