package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomType;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeMapped extends TwinAttributeMapped implements CustomType {

	protected Set<TwinAttributeMapped> fields = new HashSet<>();

	public CustomTypeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getFields() {

		return new ArrayList<>(fields);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		if (getSysmlElement() instanceof org.omg.sysml.lang.sysml.Usage) {
			super.parse(context);
		}
		fields = new HashSet<>(context.mapSlot(this, "fields", TwinAttributeMapped.class));
	}

}
