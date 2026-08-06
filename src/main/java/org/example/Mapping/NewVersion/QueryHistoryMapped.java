package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.QueryHistory;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.PartUsage;


@MappedElementType(LibraryNameSpaces.QUERY_HISTORY)
@ToString(callSuper = true)
public class QueryHistoryMapped extends AbstractMappedQuery implements QueryHistory {

	public QueryHistoryMapped(PartUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);
		checkMatchingAttributeTypes();
	}

	private void checkMatchingAttributeTypes() throws MappingException {

		for (TwinAttributeMapped queriedAttribute : twinAttributes) {
			for (TwinAttributeMapped resultAttribute : result) {

				if (queriedAttribute.getClass() != resultAttribute.getClass()) {

					throw new MappingException("QueryHistory '%s': queried attribute type '%s' does not match result type '%s'.".formatted(getName(), queriedAttribute.getClass().getSimpleName(), resultAttribute.getClass().getSimpleName()));
				}
			}
		}
	}
}