package org.example.Mapping.NewVersion.Database.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.DataBase.Defintion.RelationalDatabaseDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.RELATIONAL_DATABASE)
@ToString(callSuper = true)
public class RelationalDatabaseDefinitionMapped extends DataBaseDefinitionMapped implements RelationalDatabaseDefinition {
	public RelationalDatabaseDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
