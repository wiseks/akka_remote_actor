package com.benq.loginserver;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.benq.common.Constant;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApplicationLoginServerMain {

    public static void main(String[] args) {
        startLoginSystem();
    }

    public static void startLoginSystem() {
        Config cfg = ConfigFactory.load(Constant.LoginActorCfgName);
        ActorSystem actorSystem = ActorSystem.create(Constant.LoginActorSysName, cfg);
        System.out.println("Started Login System");

        final ActorRef loginActor = actorSystem.actorOf(Props.create(LoginActor.class),
                Constant.LoginActorRemoteName);

        System.out.println("---- Login System-----");
    }

}
