package PositionThings{
    private import UserLibrary::*;
    private import BatteryControlUnitLibrary::*;

     attribute def Position:>TwinCustomType{
        attribute x[1]:TwinInteger:>fields;
        attribute y[1]:TwinInteger:>fields;
        attribute z[1]:TwinInteger:>fields;
    }

    calc def AddPosition:>CustomCalculationAction{
        in attribute pos_a[1]:>>inputs:Position;
        in attribute pos_b[1]:>>inputs:Position;
        return pos_c[1]:>>outputs:Position;
        assign pos_c := new Position(x = PLUS_int(pos_a.x, pos_b.x), y = PLUS_int(pos_a.y, pos_b.y), z = PLUS_int(pos_a.z, pos_b.z));
        
    }

    calc def Avg:>CustomCalculationAction{
        in attribute reals[0..*]:TwinReal:>>inputs;
        return out attribute avg:TwinReal:>>outputs;
        

        for value:TwinReal in reals{
            assign avg := PLUS_real(avg, value);
        }
        assign avg := DIV_real(avg, SIZE_seq(reals));
    }
}