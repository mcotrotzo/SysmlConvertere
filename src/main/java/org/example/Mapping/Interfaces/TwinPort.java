package org.example.Mapping.Interfaces;

import java.util.List;
import java.util.Optional;

public interface TwinPort extends Model {
	Optional<Protocol> getProtocol();

	List<TwinAttribute> getAttributes();

}
