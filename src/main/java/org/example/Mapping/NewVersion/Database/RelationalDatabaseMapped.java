package org.example.Mapping.NewVersion.Database;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.DataBase.RelationalDatabase;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.RELATIONAL_DATABASE)
@ToString(callSuper = true)
public class RelationalDatabaseMapped<Z extends TypeKind> extends DatabaseMapped<Z> implements RelationalDatabase<Z> {
	public RelationalDatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
