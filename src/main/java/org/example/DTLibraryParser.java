package org.example;


import Main.SysmlConverterMain;
import Executor.SemanticException;
import org.example.GenerelRules.PreRuleExecutorImpl;

import java.io.IOException;

public class DTLibraryParser {
	public final SysmlConverterMain sysmlConverterMain;

	public DTLibraryParser(String libraryZip) throws IOException {
		sysmlConverterMain = new SysmlConverterMain("DTLibrary.zip",new PreRuleExecutorImpl());
	}

	public void parse(String ...path) throws IOException, SemanticException {
		sysmlConverterMain.parse(path);

	}


}
