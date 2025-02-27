package java_practice.marsCalendar;

public class EarthDefenseHeadquarters {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        CalController calController = new CalController(userInput);
        calController.calculation();
    }
}
