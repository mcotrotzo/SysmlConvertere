package org.example.Mapping;

import lombok.Getter;
import lombok.ToString;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionFactory;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

@LibraryElement("Base::DataValue")
@ToString(
        callSuper = true)
@Getter
public class TwinAttributeRaw extends Raw{
    public TwinAttributeRaw(Type sysmlElement) {
        super(sysmlElement);
    }


    private Set<TwinAttributeRaw> twinAttributes = new HashSet<>();


    private Set<TwinExpression<?>> twinExpressions = new HashSet<>();
    private final TwinExpressionFactory twinExpressionFactory = new TwinExpressionFactory();

    public void parseExpressions() {
        for (Element element:this.getSysmlElement().getOwnedMember()){
            if (element instanceof Expression f){
                var h = twinExpressionFactory.create(f);
                System.out.println("Created twin expression: " + h);
                twinExpressions.add(h);

            }
        }
    }
}
