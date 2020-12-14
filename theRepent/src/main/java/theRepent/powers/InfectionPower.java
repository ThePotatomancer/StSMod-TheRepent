package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makePowerPath;

public class InfectionPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    private int TurnInfectionUsage;

    public static final String POWER_ID = RepentMod.makeID("Infection");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Infection_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Infection_power32.png"));

    public InfectionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0];
    }

    /*@Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        int remainingDamage = Math.max(info.base - amount, 0);
        TurnInfectionUsage = Math.max(info.base - remainingDamage, TurnInfectionUsage);
        return remainingDamage;
    }*/

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL &&
                (owner.isPlayer || ((AbstractMonster)owner).getIntentBaseDmg() == damage )) {
            float remainingDamage = Math.max(damage - amount, 0);
            TurnInfectionUsage = (int) Math.max(damage - remainingDamage, TurnInfectionUsage);
            return remainingDamage;
        }
        else return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        reducePower(TurnInfectionUsage);
        TurnInfectionUsage = 0;
        if (amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new InfectionPower(owner, source, amount);
    }
}
