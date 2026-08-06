package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

@MappedElementType(LibraryNameSpaces.CONTROL_UNIT_STATE)
@ToString(callSuper = true)
public class ControlUnitStateMapped extends StateMachineMapped {

	public ControlUnitStateMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}
}