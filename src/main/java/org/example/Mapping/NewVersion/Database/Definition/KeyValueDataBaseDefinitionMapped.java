package org.example.Mapping.NewVersion.Database.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.DataBase.Defintion.KeyValueDatabaseDefintion;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(LibraryNameSpaces.KEY_VALUE_DATABASE)
@ToString(callSuper = true)
public class KeyValueDataBaseDefinitionMapped extends DataBaseDefinitionMapped implements KeyValueDatabaseDefintion {
	public KeyValueDataBaseDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
