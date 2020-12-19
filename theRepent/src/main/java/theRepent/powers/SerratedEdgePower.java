package theRepent.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makePowerPath;


public class SerratedEdgePower extends AbstractPower implements CloneablePowerInterface {

    public AbstractCreature source;

    public static final String POWER_ID = RepentMod.makeID("SerratedEdgePower");
    private static final Logger logger = LogManager.getLogger(SerratedEdgePower.class.getName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final int MAX_STACK = 3;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("SerratedEdge_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("SerratedEdge_power32.png"));

    public SerratedEdgePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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

    // TODO: unblocked damage only!
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            int bleedAmount = (int)Math.ceil((float)damageAmount * ((float)this.amount / 3f));
            if (bleedAmount > 0) {
                this.flash();
                this.addToTop(new ApplyPowerAction(target, this.owner, new BleedPower(target, this.owner, bleedAmount)));
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        int finalAmount = Math.min(stackAmount + amount, MAX_STACK);
        int addedStack = finalAmount - amount;
        if (addedStack < stackAmount) {
            logger.info(this.name + " does not stack beyond " + MAX_STACK + ", " +
                    (stackAmount - addedStack) + " stacks lost.");
        }
        super.stackPower(addedStack);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (33 * this.amount) + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SerratedEdgePower(owner, source, amount);
    }
}
