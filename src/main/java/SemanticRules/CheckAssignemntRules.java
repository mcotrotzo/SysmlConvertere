package SemanticRules;

import org.example.Mapping.Interfaces.*;
import org.example.TwinDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckAssignemntRules implements SemanticRule {


	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {
		return true;
	}

}