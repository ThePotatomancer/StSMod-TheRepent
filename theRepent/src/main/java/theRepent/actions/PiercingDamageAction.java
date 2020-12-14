package theRepent.actions;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theRepent.patches.ExtendedDamageTypes;

public class PiercingDamageAction extends DamageAction {
    public PiercingDamageAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect) {
        super(target, new DamageInfo(source, amount, ExtendedDamageTypes.PIERCING), effect);
    }
}
