package com.benq.matchserver;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.benq.common.Constant;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationMatchServerMain {

    public static void main(String[] args) {
        startMatchSystem();
    }

    public static void startMatchSystem() {
        Config cfg = ConfigFactory.load(Constant.MatchActorCfgName);
        ActorSystem actorSystem = ActorSystem.create(Constant.MatchActorSysName, cfg);
        System.out.println("Started Match System");

        final ActorRef matchActor = actorSystem.actorOf(Props.create(MatchActor.class),
                Constant.MatchActorRemoteName);

        System.out.println("---- Match System-----");
    }

}
