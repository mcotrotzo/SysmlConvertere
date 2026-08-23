package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinStateMachine.TwinStateMachineMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STATE)
@ToString(callSuper = true)
public class DescriptiveTwinStateMapped extends TwinStateMachineMapped {

	public DescriptiveTwinStateMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}
}
