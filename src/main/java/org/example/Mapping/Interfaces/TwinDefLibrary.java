package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Package;
import org.example.Mapping.Interfaces.FullTwin.Twin;

import java.util.List;

public interface TwinDefLibrary extends Package {
	List<Twin> getTwins();
}
