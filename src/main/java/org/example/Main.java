package org.example;

import org.eclipse.emf.ecore.EObject;

import org.example.GenerelRules.GenerelRules;
import org.example.GenerelRules.LibraryElements;
import org.example.GenerelRules.RedefintionRules;
import org.omg.sysml.interactive.SysMLInteractive;
import org.omg.sysml.interactive.SysMLInteractiveResult;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.*;
import org.eclipse.xtext.diagnostics.Severity;
import org.omg.sysml.xtext.validation.SysMLValidator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import static org.example.GenerelRules.LibraryElements.TWIN;

public class Main {

    public static void copyToSysMLLibrary(File src, File dst) {
        if (src.isDirectory()) {
            if (!dst.exists()) {
                dst.mkdirs();
            }
            for (File file : Objects.requireNonNull(src.listFiles())) {
                copyToSysMLLibrary(file, new File(dst, file.getName()));
            }
        } else {
            try {
                Files.copy(src.toPath(), dst.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        SysMLInteractive interactive = SysMLInteractive.getInstance();

        //Loading of the libraries
        //TODO Refactor this
        File libraryDir = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\target\\sysml-download\\sysml\\sysml.library");
        File dtLibrary = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\DTLibrary");
        File userLibrary = new File("C:\\Users\\marco\\Git-projects\\SysmlTest\\UserDefinedLibrary");
        copyToSysMLLibrary(dtLibrary, Path.of(libraryDir.getAbsolutePath(),"Systems Library").toFile());
        copyToSysMLLibrary(userLibrary, Path.of(libraryDir.getAbsolutePath(),"Systems Library").toFile());
        interactive.loadLibrary(libraryDir.getAbsolutePath());


        //Loading of the model
        String content = Files.readString(Path.of("C:\\Users\\marco\\Git-projects\\SysmlTest\\UserModel\\Test.sysml"));
        SysMLInteractiveResult result = interactive.process(content, true);

        result.getIssues().forEach(i -> {
            System.err.println(i.getSeverity()+": " + i.getMessage()+ " at " + i.getLineNumber() + ":" + i.getColumn());
        });

        if (result.getIssues().stream().anyMatch(i -> i.getSeverity() == Severity.ERROR)) {
            return;
        }

        GenerelRules generalRules = new RedefintionRules();

        generalRules.isValid(result.getRootElement());

    }


}