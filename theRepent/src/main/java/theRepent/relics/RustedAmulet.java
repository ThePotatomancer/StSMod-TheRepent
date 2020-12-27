package theRepent.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theRepent.RepentMod;
import theRepent.blights.PriceOfMistakes;
import theRepent.util.Blights;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makeRelicOutlinePath;
import static theRepent.RepentMod.makeRelicPath;

public class RustedAmulet extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    public static final String ID = RepentMod.makeID("RustedAmulet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rustedAmulet_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("rustedAmulet_relic.png"));

    public static final int STR_REDUCTION = 2;
    private static final Float HEAL_RATIO = 0.15F;

    public RustedAmulet() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void onVictory() {
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new RelicAboveCreatureAction(player, this));
        if (player.currentHealth > 0) {
            player.heal((int)(player.maxHealth * HEAL_RATIO));
        }
    }

    @Override
    public void onEquip() {
        AbstractBlight blight = new PriceOfMistakes();
        Blights.spawnBlightAndObtain((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, blight);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STR_REDUCTION + DESCRIPTIONS[1] + (int)(HEAL_RATIO * 100) + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RustedAmulet();
    }
}
