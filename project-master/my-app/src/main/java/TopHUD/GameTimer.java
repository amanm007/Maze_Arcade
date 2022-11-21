package TopHUD;

/**
 * used to measure elapsed game time
 */
public class GameTimer {

    long programStartTime;
    long currentTime;
    long totalTime;
    long minutes;
    long seconds;
    String stringMinutes;
    String stringSeconds;

    /**
     * saves start time of game.
     */
    public GameTimer() {
        programStartTime = System.currentTimeMillis() / 1000;
    }

    /**
     * sets start time to current time
     */
    public void resetProgramStartTime() {
        programStartTime = System.currentTimeMillis() / 1000;
    }

    /**
     * returns elapsed time in string form
     *
     * @return elapsedTime
     */
    public String getTime() {
        currentTime = System.currentTimeMillis() / 1000;

        totalTime = currentTime - programStartTime;

        minutes = (long) Math.floor((totalTime / 60));
        seconds = totalTime - (minutes * 60);

        stringMinutes = String.valueOf(minutes);
        stringSeconds = String.valueOf(seconds);

        if (seconds < 10) {
            return stringMinutes + ":0" + stringSeconds;
        } else {
            return stringMinutes + ":" + stringSeconds;
        }
    }
}

