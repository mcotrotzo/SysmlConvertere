package org.example.Mapping.NewVersion.Database;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.DataBase.KeyValueDatabase;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.KEY_VALUE_DATABASE)
@ToString(callSuper = true)
public class KeyValueDataBaseMapped<Z extends TypeKind> extends DatabaseMapped<Z> implements KeyValueDatabase<Z> {
	public KeyValueDataBaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
