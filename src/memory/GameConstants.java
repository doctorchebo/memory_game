package memory;

public class GameConstants {
    private GameConstants() { }
    public static final int NUMBER_OF_ROWS = 4;
    public static final int NUMBER_OF_COLUMNS = 6;
    public static final int NUMBER_OF_PAIRS = (NUMBER_OF_ROWS*NUMBER_OF_COLUMNS)/2;
    public static final String URL_SUCCESS_ICON = "/src/images/done";
    public static final String URL_UNKNOWN_ICON = "/src/images/unknown";
    public static final String FRAME_NAME = "Memory";
    public static final String NEW_GAME_BTN = "New Game";
    public static final String ABOUT_BTN = "About";
    public static final String SOLVE_BTN = "Solve";
    public static final String COUNT_LABEL = "Number of clicks: ";
    public static final String SOLVE_MESSAGE = "Auto Resolution";
    public static final String ABOUT_LABEL = "Just for fun :), refactored by Marcelo Muñoz ;)";

}
