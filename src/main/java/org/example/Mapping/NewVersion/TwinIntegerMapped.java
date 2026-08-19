package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinIntegerAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinIntegerMapped extends TwinAttributeMapped implements TwinIntegerAttribute {
	public TwinIntegerMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
