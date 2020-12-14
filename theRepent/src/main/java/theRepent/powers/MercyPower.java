package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.patches.observables.HealEvent;
import theRepent.patches.observables.HealObservable;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makePowerPath;


public class MercyPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = RepentMod.makeID("MercyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Mercy_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Mercy_power32.png"));

    private static final HealObservable healObservable = new HealObservable();

    public MercyPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void onHealAction(HealEvent event) {
        if (event.source == owner && event.target != owner) {
            int missingHealth = event.target.maxHealth - event.target.currentHealth;
            int effectiveHeal = Math.min(missingHealth, event.amount);
            if (effectiveHeal > 0) {
                int virtueGain = (int) Math.floor(effectiveHeal / 2.0);
                flash();
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(owner, owner, new VirtuePower(owner, owner, virtueGain)));
            }
        }
    }

    @Override
    public void onInitialApplication() {
        healObservable.subscribe(this::onHealAction);
    }

    @Override
    public void onRemove() { healObservable.unsubscribe(this::onHealAction); }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new MercyPower(owner, source, amount);
    }
}
