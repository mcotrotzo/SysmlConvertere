package org.example.Mapping;


import org.example.Mapping.Mapper.CustomCalculationMapper;
import org.example.Mapping.Mapper.TwinMapper;

import java.util.Set;

public class MapperService {



    private final RawRegistry rawRegistry = RawRegistry.getInstance();





    public void map(){
        TwinMapper twinMapper = new TwinMapper();
        CustomCalculationMapper customCalculationMapper = new CustomCalculationMapper();
        Set<TwinRaw> res = twinMapper.map();
        Set<CustomCalculationRaw> res2 = customCalculationMapper.map();

        for(TwinRaw twinRaw : res){
            System.out.println(twinRaw);
        }
        for(CustomCalculationRaw customCalculationRaw : res2){
            System.out.println(customCalculationRaw);
        }
    }

}
