package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseRealDefinition;
import org.example.Mapping.Interfaces.BaseTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.ReferenceUsage;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class BaseTypeRealDefinitionMapped extends BaseTypeDefinitionMapped implements BaseRealDefinition {
	public BaseTypeRealDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

}

