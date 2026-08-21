package org.example.Mapping.NewVersion.TwinAttribute;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.TwinTypeDefinitionMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeUsageMapped extends MappedElement<Feature> implements TwinAttributeUsage {

	protected MappedReference<TwinTypeDefinitionMapped> typeReference;
	public TwinAttributeUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}


}
