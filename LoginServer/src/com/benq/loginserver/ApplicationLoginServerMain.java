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
        //user cfg
        String serverHostName= UserConfig.getConfig().getString("akka.remote.netty.tcp.hostname");
        int serverPort= UserConfig.getConfig().getInt("akka.remote.netty.tcp.port");

        //app resource cfg
        Config appCfg = ConfigFactory.load(Constant.LoginActorCfgName);
        final Config finalConfig = ConfigFactory.parseString(
                "akka.remote.netty.tcp.hostname=" + serverHostName)
                .withFallback(ConfigFactory.parseString("akka.remote.netty.tcp.port=" + serverPort))
                .withFallback(appCfg);

        ActorSystem actorSystem = ActorSystem.create(Constant.LoginActorSysName, finalConfig);
        System.out.println("Started Login System");

        final ActorRef loginActor = actorSystem.actorOf(Props.create(LoginActor.class),
                Constant.LoginActorRemoteName);

        System.out.println("---- Login System-----");
    }

}
