package PositionThings{
    private import UserLibrary::*;
    private import BatteryControlUnitLibrary::*;

     attribute def Position:>TwinCustomType{
        attribute x[1]:TwinInteger:>fields;
        attribute y[1]:TwinInteger:>fields;
        attribute z[1]:TwinInteger:>fields;
    }

    attribute def Position2:>TwinCustomType{
            attribute x[1]:Position:>fields;
        }

        calc def AddPosition:>CustomCalculationAction{
            in attribute pos_a[1]:>>inputs:Position;
            in attribute pos_b[1]:>>inputs:Position2;
            return pos_c[1]:>>outputs:Position;
            assign pos_c := new Position(x = PLUS_int(pos_a.x, pos_b.x.x), y = PLUS_int(pos_a.y, pos_b.x.y), z = PLUS_int(pos_a.z, pos_b.x.z));

        }





    calc def Avg2 :> Avg {
        in attribute x[1] :>> reals;
        in attribute y[1] :>> reals;
    }
    calc def Avg:>CustomCalculationAction{
        in attribute reals[0..*]:TwinReal:>>inputs;
        return out attribute avg:TwinReal:>>outputs;

        action test{
            for value:TwinReal in reals{
            attribute test :>>local_Attributes:TwinBoolean;
            if EQ_real(SIZE_seq(reals), 0){
                assign avg := 0;
            }
            else{
                while EQ_real(SIZE_seq(reals), 0){
                    assign avg := 0;
                    action test:TwinAction;
                }
                assign avg := 0;

            }
            assign avg := PLUS_real(avg, value);
        }
        }
        first test;
        then assign avg := 0;

        assign avg := DIV_real(avg, SIZE_seq(reals));
    }
}