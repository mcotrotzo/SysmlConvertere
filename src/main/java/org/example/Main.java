package org.example;

import org.example.GenerelRules.GenerelRules;
import org.example.GenerelRules.RedefintionAndSubsettingRules;
import org.example.GenerelRules.RedefintionRules;
import org.example.Mapping.RawRegistry;
import org.example.Mapping.MapperService;
import org.example.Util.Utils;
import org.omg.sysml.interactive.SysMLInteractive;
import org.omg.sysml.interactive.SysMLInteractiveResult;
import org.eclipse.xtext.diagnostics.Severity;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

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

    public static List<GenerelRules> getRules() {
        return List.of(new RedefintionRules(),
                        new RedefintionAndSubsettingRules());
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
        SysMLInteractiveResult result = interactive.process(content, false);

        result.getIssues().forEach(i -> {
            System.err.println(i.getSeverity()+": " + i.getMessage()+ " at " + i.getLineNumber() + ":" + i.getColumn());
        });

        if (result.getIssues().stream().anyMatch(i -> i.getSeverity() == Severity.ERROR)) {
            return;
        }
        RawRegistry rawRegistry = RawRegistry.getInstance();
        Utils utils = Utils.getInstance();
        utils.setRootElement(result.getRootElement());
        utils.setResourceSet(result.getRootElement().eResource().getResourceSet());


        boolean isValid = true;
        for (GenerelRules rule : getRules()) {
            System.out.println("Checking rule: " + rule.getClass().getSimpleName());
            boolean ruleValid = rule.isValid();
            System.out.println("Is valid: " + ruleValid);

            isValid = isValid && ruleValid;
        }

        MapperService mapper = new MapperService();
        mapper.map();
        System.out.println("Overall model validity: " + isValid);




    }



}