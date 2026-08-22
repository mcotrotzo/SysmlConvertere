package org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public abstract class TwinAttributeDefinitionMapped
        extends MappedElementDefinition<Classifier>
        implements TwinAttributeDefinition {

    private final List<Reference<? extends TwinAttributeDefinition>> superTypes = new ArrayList<>();

	public TwinAttributeDefinitionMapped(Classifier sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public List<Reference<? extends TwinAttributeDefinition>> getSuperTypes() {
        return List.copyOf(superTypes);
    }

    @Override
    public void parse(MappingContext context) throws MappingException {
       //TODO
    }
}
