package net.atos.wolf.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameSessionCleanupService extends Thread {

    private SessionService sessionService;

    private static long threadSleepDurationMS = 30000;

    private static long expirationTimeMS = 300000;

    public GameSessionCleanupService(SessionService service) {
        this.sessionService = service;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(threadSleepDurationMS);
                sessionService.cleanupStaleSessions(expirationTimeMS);
            } catch (Exception e) {
                LOG.debug("exception occurred", e);
            }
        }
    }
}
