package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STATE)
@ToString(callSuper = true)
public class DescriptiveStateMapped extends StateMachineMapped {

	public DescriptiveStateMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}
}
