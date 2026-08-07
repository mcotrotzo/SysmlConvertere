package SemanticRules;


import org.example.TwinDataBase;

public interface SemanticRule {


	public boolean isValid(TwinDataBase database) throws SemanticException;
}
