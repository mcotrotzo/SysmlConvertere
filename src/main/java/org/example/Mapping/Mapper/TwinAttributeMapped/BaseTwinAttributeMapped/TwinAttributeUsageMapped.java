package org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedElementUsage;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public abstract class TwinAttributeUsageMapped
        extends MappedElementUsage<Feature>
        implements TwinAttributeUsage {

    protected MappedReference<TwinAttributeDefinitionMapped> typeReference;

    public TwinAttributeUsageMapped(Feature sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public Reference<? extends TwinAttributeDefinitionMapped> getDefinition() {
        return typeReference;
    }

    @Override
    public void parse(MappingContext context) throws MappingException {
        //TODO
    }

	@Override
	public Role getRole() {
	  //TODO;
		return null;
	}

	@Override
	public Direction getDirection() {
		//TODO;
		return null;
	}
}
