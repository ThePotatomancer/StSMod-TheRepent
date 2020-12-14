package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makePowerPath;

public class RedemptionPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = RepentMod.makeID("Redemption");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Redemption_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Redemption_power32.png"));

    public RedemptionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new DamageAction(owner, new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS)));
            flashWithoutSound();
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new RedemptionPower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() { return amount; }

    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }
}
