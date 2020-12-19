package theRepent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theRepent.powers.RedemptionPower;

import java.util.List;

public class SetMaxHealthAction extends AbstractGameAction {
    AbstractCreature target;
    Integer newMaxHealth;

    public SetMaxHealthAction(AbstractCreature target, Integer newMaxHealth) {
        this.target = target;
        this.newMaxHealth = newMaxHealth;
    }

    @Override
    public void update() {
        float healthRatio = (float)target.currentHealth / (float)target.maxHealth;
        int healthDiff = newMaxHealth - target.maxHealth;
        if (healthDiff > 0) {
            target.increaseMaxHp(healthDiff, true);
        }
        else if (healthDiff < 0) {
            target.decreaseMaxHealth(-healthDiff);
        }
        target.currentHealth = (int)(healthRatio * target.maxHealth);
        target.healthBarUpdatedEvent();
        isDone = true;
    }
}
