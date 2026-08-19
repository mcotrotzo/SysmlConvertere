package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinBooleanAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class TwinBooleanMapped extends TwinAttributeMapped implements TwinBooleanAttribute {
	public TwinBooleanMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
