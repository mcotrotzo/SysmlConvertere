package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.QueryHistory;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Function;
import org.omg.sysml.lang.sysml.PartUsage;


@MappedElementType(LibraryNameSpaces.QUERY_HISTORY)
@ToString(callSuper = true)
public class QueryHistoryMapped extends AbstractMappedQuery implements QueryHistory {

	public QueryHistoryMapped(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);
		checkMatchingAttributeTypes();
	}

	private void checkMatchingAttributeTypes() throws MappingException {

		var twinAttributeType =
				twinAttribute.getDefinition().getReferent();

		var resultType =
				result.getDefinition().getReferent();

		if (twinAttributeType != resultType) {
			throw new MappingException(
					"QueryHistory '%s': twinAttribute type '%s' must match result type '%s'."
							.formatted(
									getName(),
									twinAttributeType.getName(),
									resultType.getName()
							)
			);
		}
	}
}