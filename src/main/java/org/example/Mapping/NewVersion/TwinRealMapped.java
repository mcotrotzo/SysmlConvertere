package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinRealAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class TwinRealMapped extends TwinAttributeMapped implements TwinRealAttribute {
	public TwinRealMapped(Type sysmlElement) {
		super(sysmlElement);
	}


}
