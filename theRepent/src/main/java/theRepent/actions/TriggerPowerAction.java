package theRepent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.List;

public class TriggerPowerAction extends AbstractGameAction {
    AbstractCreature target;
    List<String> powerIds;

    public TriggerPowerAction(AbstractCreature target, List<String> powerIds) {
        this.target = target;
        this.powerIds = powerIds;
    }

    @Override
    public void update() {
        for (final String powerId : powerIds) {
            AbstractPower currentPower = target.getPower(powerId);

            if (currentPower != null) {
                currentPower.atStartOfTurn();
                currentPower.atEndOfTurn(true);
                currentPower.atEndOfRound();
            }
        }

        isDone = true;
    }
}
