package org.example.Mapping.TwinAction;

import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.omg.sysml.lang.sysml.ActionUsage;

public abstract class AbstractTwinActionMapped extends MappedElement<ActionUsage> implements Action {

	public AbstractTwinActionMapped(ActionUsage sysmlElement) {
		super(sysmlElement);
	}
}