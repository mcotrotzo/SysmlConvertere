package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(LibraryNameSpaces.QUERY_RESULT)
@ToString(callSuper = true)
public class QueryResultDefinitionMapped extends CustomAttributeMappedDefintion implements CustomTypeDefinition {
	public QueryResultDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}


}
