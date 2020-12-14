package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnvenomPower;
import theRepent.RepentMod;


public class DipVenomPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = RepentMod.makeID("DipVenomPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DipVenomPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("shackle");

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        flash();
        AbstractPower envenomPower = owner.getPower(EnvenomPower.POWER_ID);
        if (envenomPower.amount - amount > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new EnvenomPower(owner, -amount)));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, EnvenomPower.POWER_ID));
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public AbstractPower makeCopy() {
        return new DipVenomPower(owner, source, amount);
    }
}
