package org.example.Mapping.NewVersion.Database.Definition;

import org.example.Mapping.Interfaces.DataBase.Defintion.DataBaseDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Database.DatabaseMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Type;
@MappedElementType(LibraryNameSpaces.DATABASE)
public abstract class DataBaseDefinitionMapped extends DatabaseMapped<Classifier> implements DataBaseDefinition {
	public DataBaseDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
