package theRepent.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theRepent.RepentMod;
import theRepent.actions.SetMaxHealthAction;
import theRepent.util.TextureLoader;

import static theRepent.RepentMod.makeRelicOutlinePath;
import static theRepent.RepentMod.makeRelicPath;

public class RustedLocket extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    public static final String ID = RepentMod.makeID("RustedLocket");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rustedLocket_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("rustedLocket_relic.png"));

    private static final Float HEALTH_BOOST_RATIO = 2f;
    private static final Float HEAL_RATIO = 0.15f;

    public RustedLocket() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        for (final AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                addToBot(new SetMaxHealthAction(monster, (int)(monster.maxHealth * HEALTH_BOOST_RATIO)));
            }
        }
    }

    @Override
    public void onVictory() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            addToBot(new HealAction(player, player, (int)(player.maxHealth * HEAL_RATIO)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEALTH_BOOST_RATIO + DESCRIPTIONS[1] + HEAL_RATIO * 10 + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RustedLocket();
    }
}
