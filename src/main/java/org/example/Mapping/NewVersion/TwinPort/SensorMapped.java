package org.example.Mapping.NewVersion.TwinPort;


import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.SENSOR)
@ToString(callSuper = true)
public class SensorMapped<T extends Type> extends TwinPortMapped<T> implements Sensors {

	private List<TwinAttributeUsageMapped> attributes = new ArrayList<>();

	public SensorMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttributeUsage> getAttributes() {
		return new ArrayList<>(attributes);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = context.mapSlot(this, "measurements", TwinAttributeUsageMapped.class);
	}
}