package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.CONST_PORT)
@ToString(callSuper = true)
public class ConstPortMapped<T extends Type> extends TwinPortMapped<T> implements ConstPort {
	private List<TwinAttributeUsageMapped> attributes = new ArrayList<>();

	public ConstPortMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttributeUsage> getAttributes() {
		return new ArrayList<>(attributes);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = context.mapAttributes(this, "measurements", TwinAttributeUsageMapped.class, Role.CONST);
	}
}
