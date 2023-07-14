package net.atos.wolf.services.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.SessionService;

import java.time.LocalDate;
@Slf4j
public class GameSessionObserver extends Thread{

    private SessionService sessionService;

    private static long threadSleepDuration;

    private static long sessionExpireTime;

    public GameSessionObserver(SessionService service) {
        this.sessionService = service;
    }


    public void run() {
        LOG.debug("CREATION DATE");
        while (true) {
            try {
                Thread.sleep(3000);
//                for (int i = 0; i < sessionService.serviceHashMap().size(); i++) {
//
//                    LocalDate localDate = LocalDate.now().minusDays(sessionService.serviceHashMap().get(i).creationDate().toEpochDay());
//
//                    System.out.println(localDate);
//                }
            } catch (Exception e) {
                LOG.debug("exception occurred", e);
            }


        }
    }
}
