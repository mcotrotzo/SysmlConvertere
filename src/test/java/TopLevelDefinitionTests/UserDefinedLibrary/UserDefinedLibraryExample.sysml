package PositionThings{
    private import UserLibrary::*;
    private import BatteryControlUnitLibrary::*;

     attribute def Position:>TwinCustomType{
        attribute x[1]:TwinInteger:>fields;
        attribute y[1]:TwinInteger:>fields;
        attribute z[1]:TwinInteger:>fields;
    }

    attribute def GeoPosition:>Position{

    }

    calc def AddPosition:>CustomCalculationAction{
        in attribute pos_a[1]:>>inputs:Position;
        in attribute pos_b[1]:>>inputs:Position;
        return pos_c[1]:>>outputs:Position;
        assign pos_c := new Position(x = PLUS_int(pos_a.x, pos_b.x), y = PLUS_int(pos_a.y, pos_b.y), z = PLUS_int(pos_a.z, pos_b.z));

    }

    calc def Avg3:>CustomCalculationAction{
           return out attribute avg:TwinReal:>>outputs;

        }
    calc def Temp30 :>QueryHistory{
            in attribute :>>twinAttribute:TwinReal[1];
            in attribute :>>since = 30;
            in attribute :>>sinceUnit = TimeUnit::SECOND;
            return attribute :>>result:TwinReal[0..*];

        }
        calc def PositionHistory :> GroupedHistoryQuery {
            in attribute :>>twinAttribute:Position[1];
            in attribute :>>since = 30;
            in attribute sinceUnit:>>sinceUnit = TimeUnit::SECOND;
            return attribute :>>result:PositionQueryResult[0..*];
        }
        calc def LastReal:>QueryHistory{
                in :>>twinAttribute:TwinReal[1];
                in :>>since;
                in :>>sinceUnit = TimeUnit::SECOND;
                in :>>orderBy = ORDER_BY::DESCENDING;
                return :>>result:TwinReal[0..*];
            }
            attribute def PositionQueryResult:>QueryResult{
                x:>result:Position[0..*];
            }

    calc def Avg2:>CustomCalculationAction{
            in attribute reals[0..*]:TwinReal:>>inputs;
            return out attribute avg:TwinReal:>>outputs;

            for value:TwinReal in reals{
                attribute test :>local_Attributes:TwinBoolean;
                if EQ_real(SIZE_seq(reals), 0){
                    assign avg := 0;
                }
                else{
                    while EQ_real(SIZE_seq(reals), 0){
                        assign avg := 0;
                    }
                    assign avg := 0;
                }
                assign avg := PLUS_real(avg, value);
            }
            assign avg := 0;
            assign avg := DIV_real(avg, SIZE_seq(reals));
        }

        calc def Avg:>CustomCalculationAction{
                in attribute reals[0..*]:TwinReal:>>inputs;
                return out attribute avg:TwinReal:>>outputs;

                attribute test23 :>>local_Attributes:Position = new Position(x=0, y=0, z=0);

                action test{
                    for value:TwinReal in reals{

                    if EQ_real(SIZE_seq(reals), 0){
                        assign avg := 0;
                    }
                    else{
                        while EQ_real(SIZE_seq(reals), 0){
                            action test5 {
                                assign avg := 0;
                            }
                            action test6 {
                                assign avg := 0;
                            }
                            first test5;
                            then test6;


                        }
                        assign avg := 0;
                    }
                    assign avg := PLUS_real(avg, value);
                }
                }

                assign avg := 0;

                assign avg := DIV_real(avg, SIZE_seq(reals));
            }
}