package edu.ntnu.texasai.controller;

import edu.ntnu.texasai.model.*;

import java.util.List;

public class PlayerControllerPhaseI extends PlayerController {
    private final HandPowerRanker handPowerRanker;

    public PlayerControllerPhaseI() {
        // TODO: Add Di
        handPowerRanker = new HandPowerRanker();
    }

    @Override
    public BettingDecision decidePreFlop(Player player, GameHand gameHand, List<Card> cards) {
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);

        if (card1.getNumber().equals(card2.getNumber())) {
            return BettingDecision.RAISE;
        } else if (card1.getNumber().getPower() + card2.getNumber().getPower() > 16) {
            return BettingDecision.CALL;
        } else {
            return BettingDecision.FOLD;
        }
    }

    @Override
    public BettingDecision decideAfterFlop(Player player, GameHand gameHand, List<Card> cards) {
        HandPower handPower = handPowerRanker.rank(cards);

        HandPowerType handPowerType = handPower.getHandPowerType();
        if (handPowerType.equals(HandPowerType.NOTHING)) {
            return BettingDecision.FOLD;
        } else if (handPowerType.getPower() >= HandPowerType.THREE_OF_A_KIND.getPower()) {
            return BettingDecision.RAISE;
        } else {
            return BettingDecision.CALL;
        }
    }
}