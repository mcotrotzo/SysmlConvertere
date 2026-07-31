package org.example.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
public class LibraryUtils {


    private Utils utils;


    private Map<LibraryNameSpaces,Type> libraryMap = new HashMap<>();
    private LibraryElementUtils libraryElementUtils;


    public LibraryUtils(Element rootElement) {
        for(LibraryNameSpaces libraryNameSpaces : LibraryNameSpaces.values()){
            libraryMap.put(libraryNameSpaces,SysMLLibraryUtil.getLibraryType(rootElement, String.valueOf(libraryNameSpaces)));
        }
    }



}
