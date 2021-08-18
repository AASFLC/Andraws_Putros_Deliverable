package gameRules;

/**
 * Andraws Putros
 * Student ID: 991407087
 */
public class Betting {
    public double win(double playerPool, double playerBet) {
        return playerPool + playerBet;
    }
    
    public double lose(double playerPool, double playerBet) {
        return playerPool - playerBet;
    }
}
