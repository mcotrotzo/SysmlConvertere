package FederatedBattery{
    private import TwinLibrary::*;
    private import Test::*;


    part def FederatedBattery :> FederatedTwin {
             flow :>federatedLinks from pv.pv_desc.test.test to battery.descriptiveBattery.LLM_Request.soc{
                 :>>linkType = FederatedLinkType::PUSH;
             }
         }
}