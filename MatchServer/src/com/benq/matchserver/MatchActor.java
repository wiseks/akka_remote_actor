package com.benq.matchserver;

import akka.actor.UntypedActor;
import com.benq.common.Op;

public class MatchActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Op.Multiply) {
            Op.Multiply multiply = (Op.Multiply) message;
            System.out.println("Calculating " + multiply.getN1() + " * "
                    + multiply.getN2());
            Op.MultiplicationResult result = new Op.MultiplicationResult(
                    multiply.getN1(), multiply.getN2(), multiply.getN1()
                    * multiply.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof Op.Divide) {
            Op.Divide divide = (Op.Divide) message;
            System.out.println("Calculating " + divide.getN1() + " / "
                    + divide.getN2());
            Op.DivisionResult result = new Op.DivisionResult(divide.getN1(),
                    divide.getN2(), divide.getN1() / divide.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof Op.Integral) {
            Op.Integral integral = (Op.Integral) message;
            System.out.println("Calculating " + integral.getN1() + "*"
                    + integral.getN2()+ "*"
                    + integral.getN2());
            Op.IntegralResult result = new Op.IntegralResult(integral.getN1(),
                    integral.getN2(), integral.getN1() * integral.getN2()*integral.getN2());
            getSender().tell(result, getSelf());
        } else if (message instanceof String) {
            System.out.println("get msg " + message);
            getSender().tell("resp for " + message, getSelf());
        } else {
            unhandled(message);
        }
    }
}
