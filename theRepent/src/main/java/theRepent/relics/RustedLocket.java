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
import theRepent.blights.PriceOfRegrets;
import theRepent.util.Blights;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makeRelicOutlinePath;
import static theRepent.RepentMod.makeRelicPath;

/*
    Relic Hp_Mult is implemented by applying a postfix patch to AbstractMonster -
    in constructor and in setHp. This is to copy vanilla blight behavior and prevent shenanigans
    like split slime having the same hp as the slime they split from.
 */
public class RustedLocket extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    public static final String ID = RepentMod.makeID("RustedLocket");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rustedLocket_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("rustedLocket_relic.png"));

    public static final Float HEALTH_BOOST_RATIO = 2F;
    private static final Float HEAL_RATIO = 0.15F;

    public RustedLocket() {
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
        AbstractBlight blight = new PriceOfRegrets();
        Blights.spawnBlightAndObtain((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, blight);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEALTH_BOOST_RATIO + DESCRIPTIONS[1] + (int)(HEAL_RATIO * 100) + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RustedLocket();
    }
}
