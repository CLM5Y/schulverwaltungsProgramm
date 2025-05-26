package service;

public class Sleep {

    /**
     * Pausiert den aktuellen Thread für die angegebene Millisekunden.
     * @param millis Dauer in Millisekunden
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep interrupted: " + e.getMessage());
        }
    }
}