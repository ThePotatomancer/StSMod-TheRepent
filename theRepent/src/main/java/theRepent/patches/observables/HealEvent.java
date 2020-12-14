package theRepent.patches.observables;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class HealEvent {
    public AbstractCreature target;
    public AbstractCreature source;
    public int amount;

    public HealEvent(AbstractCreature target, AbstractCreature source, int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
    }
}
