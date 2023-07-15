package net.atos.wolf.services.server;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.services.GameSession;
import net.atos.wolf.services.SessionService;

import java.time.LocalDate;

@Slf4j
public class GameSessionObserver extends Thread {

    private SessionService sessionService;

    private static long threadSleepDuration = 10000;

    private static long ExpireTime = 35000;

    public GameSessionObserver(SessionService service) {
        this.sessionService = service;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(threadSleepDuration);
                sessionService.serviceHashMap().forEach((key, value) -> {
                    long sessionExpireTime = System.currentTimeMillis() - value.lastUsed();
                    System.out.println(sessionExpireTime);
                    if (sessionExpireTime > ExpireTime) {
                        sessionService.serviceHashMap().remove(key, value);
                    }
                });
            } catch (Exception e) {
                LOG.debug("exception occurred", e);
            }


        }
    }
}
