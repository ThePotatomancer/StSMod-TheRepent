package theRepent.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theRepent.cards.AbstractDefaultCard;

import static theRepent.RepentMod.makeID;

public class DefaultSecondMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("SecondMagic");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDefaultCard) card).isDefaultSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractDefaultCard) card).defaultSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDefaultCard) card).defaultBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDefaultCard) card).upgradedDefaultSecondMagicNumber;
    }
}