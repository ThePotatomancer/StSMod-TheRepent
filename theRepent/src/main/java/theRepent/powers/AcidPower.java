package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makePowerPath;


public class AcidPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = RepentMod.makeID("Acid");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Acid_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Acid_power32.png"));

    public AcidPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        int damageOverflow = (amount - owner.currentBlock);
        addToBot(new LoseBlockAction(owner, source, amount));
        if (owner.hasPower(CausticAcidPower.POWER_ID) && damageOverflow > 0) {
            int causticAcidAmount = owner.getPower(CausticAcidPower.POWER_ID).amount;
            addToBot(new DamageAction(owner, new DamageInfo(source, damageOverflow * causticAcidAmount, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.POISON));
        }
        flashWithoutSound();
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            int loss = 1;
            if (owner.hasPower(CausticAcidPower.POWER_ID)) {
                AbstractPower modifier = owner.getPower(CausticAcidPower.POWER_ID);
                loss += modifier.amount;
            }
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, loss));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new AcidPower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
        int damageOverflow = Math.max(amount - owner.currentBlock, 0);
        if (owner.hasPower(CausticAcidPower.POWER_ID) && damageOverflow > 0) {
            int causticAcidAmount = owner.getPower(CausticAcidPower.POWER_ID).amount;
            return damageOverflow * causticAcidAmount;
        }
        return 0;
    }

    @Override
    public Color getColor() {
        return Color.FOREST;
    }
}
