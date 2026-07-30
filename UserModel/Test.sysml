part tettttr;

package Test{

    private import TwinLibrary::*;

    private import BatteryControlUnitLibrary::*;
    private import TwinTypeSystem::TwinReal;
    private import TwinTypeSystem::*;
    private import UserLibrary::*;
    private import BatteryControlUnitLibrary::*;
    private import PositionThings::*;

   part def Test:>Twin{
   }
   attribute tzest:TwinInteger =1;
   part test3{
    attribute tet[3];
   }

   part def Test2:>Test,Twin{
    part{
    hallo:>>test3.tet[1];
    hallo2d:>>test3.tet[3];


    }

    }



    part def Battery :> Twin {

        port p11 :> sensors {
            attribute temp    : TwinReal    :> measurements = (2, 3, 4);
            attribute voltage : TwinReal    :> measurements =DIV_real(y=2, x=10);
            attribute current : TwinReal    :> measurements = new TwinReal(0);
            attribute plug    : TwinBoolean :> measurements = new TwinBoolean(false);
            attribute pos     : Position    :> measurements = AddPosition(pos_a=new Position(x=10, y=20, z=30), pos_b=new Position(x=0, y=0, z=0));
            attribute pos2     : Position    :> measurements{
                                        Hallo:>>x[1] = 10;
                                        :>>y = 20;
                                        :>>z = 30;
                                    }
            attribute pos3     : Position    :> measurements{
                                                    Hallo:>>x[1] = 10;
                                                    :>>y = 20;
                                                    :>>z = 30;
                                                }
        }

        port p12 :> actuators {
            attribute charge : TwinReal :> commands = p11.temp;
        }


        attribute maxCharge        : TwinReal :> constAttributes = 100;
        attribute nominalVoltage   : TwinReal :> constAttributes = 48;
        attribute defaultNetDemand : TwinReal :> constAttributes = 0;

        state cm1 :> controlUnit {
            attribute maxCharge :> local_Attributes = maxCharge;

            state idle:>>states;
            state charging:>>states {
                do assign p12.charge := 100;
            }
            entry; then idle;
            transition
            first idle
            if p11.plug
            then charging;


            transition
            first idle
            if NOT_bool(p11.plug)
            then idle;


            transition
            first charging
            if NOT_bool(p11.plug)
            then idle;


        }


        attribute soc : TwinReal :> derivedAttributes =
            MULT_real(DIV_real(p11.voltage, nominalVoltage), 100);

        calc temp30  :>>query{
            in attribute :>>twinAttribute = p11.temp;
            in attribute :>>since = 30;
            in attribute :>>sinceUnit = TimeUnit::HOUR;
            return attribute gg:>>result:TwinReal[30];

        }

        part LLM_Request :> descriptiveStrategies {
            in attribute :>> lambdaPath = "battery/LLM_Request";
            in attribute avgTemperature :> inputs default avgTemp;
            in attribute current :> inputs default p11.current;
            attribute llmCurrent:>> outputs:TwinReal[1];
        }
        attribute avgTemp : TwinReal :> derivedAttributes = Avg(temp30.result);


        part consForecast :> predictiveStrategies {
            in attribute :>> lambdaPath = "battery/consForecast";
            in attribute avgTemperature :> inputs default avgTemp;
            in attribute current :> inputs default p11.current;
            in attribute TESTTEST;
            attribute predicted : TwinReal :> outputs;
        }


            part chargeStrategy :> prescriptiveStrategies {
                in attribute :>> lambdaPath default "battery/chargeStrategy";

                attribute :>> condition_ default LT_real(consForecast.predicted, maxCharge);
                attribute chargeCmd : TwinReal :> outputs = p12.charge;
            }




    }


}