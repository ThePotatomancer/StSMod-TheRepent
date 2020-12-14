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
        float healthRatio = (float)target.maxHealth / (float)target.currentHealth;
        target.maxHealth = newMaxHealth;
        target.currentHealth = (int)(healthRatio * target.maxHealth);
        isDone = true;
    }
}
