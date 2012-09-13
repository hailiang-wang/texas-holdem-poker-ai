package edu.ntnu.texasai.model.opponentmodeling;

public enum ContextPotOdds {
    LOW,
    HIGH;

    public static ContextPotOdds valueFor(double potOdds) {
        if (potOdds > 0.25) {
            return HIGH;
        } else {
            return LOW;
        }
    }
}
