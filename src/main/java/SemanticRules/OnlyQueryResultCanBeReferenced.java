package SemanticRules;

import org.example.Mapping.Interfaces.FeatureReference;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.TwinDataBase;

import java.util.Set;

public class OnlyQueryResultCanBeReferenced implements SemanticRule {

	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {

		Set<FeatureReference> references =
				database.get(FeatureReference.class);

		for (FeatureReference featureReference : references) {

			TwinAttribute attribute =
					featureReference.getTarget().getReferent();

			Model parent = attribute.getParent()
					.orElse(null);

			if (!(parent instanceof Query query)) {
				continue;
			}

			boolean isResult =
					query.getResult().stream()
							.anyMatch(result ->
									result.getId().equals(attribute.getId())
							);

			if (!isResult) {
				throw new SemanticException(
						"Only result attributes of a Query may be referenced. " +
								"Query '%s', referenced attribute '%s'."
										.formatted(
												query.getName(),
												attribute.getName()
										)
				);
			}
		}

		return true;
	}
}