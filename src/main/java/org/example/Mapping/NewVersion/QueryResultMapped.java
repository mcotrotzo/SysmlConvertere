package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.QueryResult;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Usage;

@MappedElementType(LibraryNameSpaces.QUERY_RESULT)
@ToString(callSuper = true)
public class QueryResultMapped extends CustomTypeMapped implements QueryResult {

	public QueryResultMapped(Usage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		if (getSysmlElement() instanceof org.omg.sysml.lang.sysml.Usage) {
			ElemWithMult queryResultMultiplicity = Utils.getMultiplicityRange(getSysmlElement());

			if (queryResultMultiplicity.getLowerBound() != 0 || queryResultMultiplicity.getUpperBound() != -1) {

				throw new MappingException("Wrong multiplicity %s. Each QueryResult in a GroupQueryHistory must have multiplicity [0..*].".formatted(getName()));
			}
		}


		if (this.getFields().size() != 1) {
			throw new MappingException("QueryResultMapped must have exactly one field which redefines/subsets result, but found: " + this.getFields().size());
		}
		var field = getFields().getFirst();
		if (!(field instanceof TwinAttributeUsageMapped mappedField)) {
			throw new MappingException("QueryResult field '%s' was mapped as '%s', expected TwinAttributeMapped.".formatted(field.getName(), field.getClass().getSimpleName()));
		}

		ElemWithMult multiplicity = Utils.getMultiplicityRange(mappedField.getSysmlElement());

		if (multiplicity.getLowerBound() != 0 || multiplicity.getUpperBound() != -1) {

			throw new MappingException("Field '%s' of QueryResult '%s' must have multiplicity [0..*].".formatted(field.getName(), getName()));
		}
	}
}