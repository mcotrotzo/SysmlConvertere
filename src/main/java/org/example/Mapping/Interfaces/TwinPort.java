package org.example.Mapping.Interfaces;

import java.util.List;

public interface TwinPort extends Model {
	List<Protocol> getProtocol();

	List<TwinAttribute> getAttributes();
}
