package theRepent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theRepent.powers.RedemptionPower;

import java.util.List;

public class QuickenDeathAction extends AbstractGameAction {
    AbstractCreature target;
    AbstractCreature source;
    List<String> powerIds;
    AbstractGameAction triggerAction;

    public QuickenDeathAction(AbstractCreature target, AbstractCreature source, List<String> powerIds) {
        this.target = target;
        this.powerIds = powerIds;
        this.source = source;
    }

    @Override
    public void update() {
        if (triggerAction == null) {
            triggerAction = new TriggerPowerAction(target, powerIds);
            addToTop(triggerAction);
        }
        else if (triggerAction.isDone) {
            for (final String powerId : powerIds) {
                if (!powerId.equals(RedemptionPower.POWER_ID)) {
                    addToBot(new RemoveSpecificPowerAction(target, source, powerId));
                }
            }
            isDone = true;
        }
    }
}
