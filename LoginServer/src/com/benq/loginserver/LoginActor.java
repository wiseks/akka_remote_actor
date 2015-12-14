package com.benq.loginserver;

import akka.actor.UntypedActor;
import com.benq.common.Op;

public class LoginActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof Op.Add) {
            Op.Add add = (Op.Add) message;
            System.out.println("Calculating " + add.getN1() + " + " + add.getN2());
            Op.AddResult result = new Op.AddResult(add.getN1(), add.getN2(),
                    add.getN1() + add.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof Op.Subtract) {
            Op.Subtract subtract = (Op.Subtract) message;
            System.out.println("Calculating " + subtract.getN1() + " - "
                    + subtract.getN2());
            Op.SubtractResult result = new Op.SubtractResult(subtract.getN1(),
                    subtract.getN2(), subtract.getN1() - subtract.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof Op.Integral) {
            //


        } else {
            unhandled(message);
        }
    }
}
