package com.benq.agentserver;

import akka.actor.*;
import akka.japi.Procedure;
import com.benq.common.Op;
import scala.concurrent.duration.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LookupActor extends UntypedActor {

    private final String path;
    private ActorRef loginActor = null;

    public LookupActor(String path) {
        this.path = path;
        sendIdentifyRequest();
    }

    private void sendIdentifyRequest() {
        ActorSelection actorSelection =getContext().actorSelection(path);
        actorSelection.tell("Pretty awesome feature", getSelf());


        actorSelection.tell(new Identify(path), getSelf());
        getContext().system().scheduler().scheduleOnce(Duration.create(3, SECONDS), getSelf(),
                ReceiveTimeout.getInstance(), getContext().dispatcher(), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ActorIdentity) {
            loginActor = ((ActorIdentity) message).getRef();
            if (loginActor == null) {
                System.out.println("Remote actor not available: " + path);
            } else {
                getContext().watch(loginActor);
                getContext().become(active, true);
            }
        } else if (message instanceof ReceiveTimeout) {
            System.out.println("get msg ReceiveTimeout");
            sendIdentifyRequest();
        } else {
            System.out.println("Not ready yet");
        }
    }

    Procedure<Object> active = new Procedure<Object>() {
        @Override
        public void apply(Object message) {
            if (message instanceof Op.MathOp) {
                // send message to server actor
                loginActor.tell(message, getSelf());

            } else if (message instanceof Op.AddResult) {
                Op.AddResult result = (Op.AddResult) message;
                System.out.printf("Add result: %d + %d = %d\n", result.getN1(),
                        result.getN2(), result.getResult());

            } else if (message instanceof Op.SubtractResult) {
                Op.SubtractResult result = (Op.SubtractResult) message;
                System.out.printf("Sub result: %d - %d = %d\n", result.getN1(),
                        result.getN2(), result.getResult());

            } else if (message instanceof Terminated) {
                System.out.println("Calculator terminated");
                sendIdentifyRequest();
                getContext().unbecome();

            } else if (message instanceof ReceiveTimeout) {
                // ignore
                System.out.println("get msg ReceiveTimeout");
            } else {
                unhandled(message);
            }

        }
    };
}
