package theRepent.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makeBlightOutlinePath;
import static theRepent.RepentMod.makeBlightPath;

public class PriceOfMistakes extends AbstractBlight {
    public static final String ID = RepentMod.makeID("PriceOfMistakes");
    private static final Texture IMG = TextureLoader.getTexture(makeBlightPath("PriceOfMistakes_blight.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeBlightOutlinePath("PriceOfMistakes_blight.png"));
    private final BlightStrings blightStrings;
    private final String NAME;
    private final String[] DESCRIPTIONS;
    public final int AMOUNT = 2;


    public PriceOfMistakes() {
        super(ID, "", "", "", true);
        blightStrings = CardCrawlGame.languagePack.getBlightString(ID);
        this.img = IMG;
        this.outlineImg = OUTLINE;
        NAME = this.name = blightStrings.NAME;
        DESCRIPTIONS = blightStrings.DESCRIPTION;
        updateDescription();
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(
                new ApplyPowerAction(player, player, new LoseStrengthPower(player, AMOUNT), AMOUNT));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1];
    }
}
