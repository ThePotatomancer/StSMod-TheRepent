package theRepent.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import theRepent.RepentMod;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makeBlightOutlinePath;
import static theRepent.RepentMod.makeBlightPath;

public class PriceOfRegrets extends AbstractBlight {
    public static final String ID = RepentMod.makeID("PriceOfRegrets");
    private static final Texture IMG = TextureLoader.getTexture(makeBlightPath("PriceOfRegrets_blight.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeBlightOutlinePath("PriceOfRegrets_blight.png"));
    private final BlightStrings blightStrings;
    private final String NAME;
    private final String[] DESCRIPTIONS;
    public final float MULTIPLIER = 2.0F;


    public PriceOfRegrets() {
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
    public float effectFloat() { return MULTIPLIER; }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + MULTIPLIER + DESCRIPTIONS[1];
    }
}
