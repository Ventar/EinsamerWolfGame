package net.atos.wolf.services;

public class CharacterDiedException extends RuntimeException {


    public CharacterDiedException() {
    }

    public CharacterDiedException(String message) {
        super(message);
    }

    public CharacterDiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterDiedException(Throwable cause) {
        super(cause);
    }

    public CharacterDiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
