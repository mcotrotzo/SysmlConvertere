package org.example;

import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.interactive.SysMLInteractive;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception, MappingException {
		SysMLInteractive interactive = SysMLInteractive.getInstance();

		//Loading of the libraries
		//TODO Refactor this
		File libraryDir = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\target\\sysml-download\\sysml\\sysml.library");
		File dtLibrary = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\DTLibrary");
		File userLibrary = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\UserDefinedLibrary");

		String content = "C:\\Users\\marco\\Git-projects\\SysmlTest\\UserModel";
		MapperService mapper = new MapperService(content, userLibrary.toString());
		mapper.map();

	}

}