package theRepent.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import theRepent.blights.PriceOfRegrets;
import theRepent.relics.RustedLocket;

import java.util.Arrays;
import java.util.List;

@SpirePatch(
        clz = AbstractMonster.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                String.class,
                String.class,
                int.class,
                float.class,
                float.class,
                float.class,
                float.class,
                String.class,
                float.class,
                float.class,
                boolean.class
        }
)
public class BlightHpMultPatchConstructor {
    private static final List<String> blacklistedMonsters = Arrays.asList(Mugger.ID);

    @SpirePostfixPatch
    public static void AbstractMonster(
            AbstractMonster __instance, String name, String id, int maxHealth,
            float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl,
            float offsetX, float offsetY, boolean ignoreBlights ) {
        if (!ignoreBlights && AbstractDungeon.player.hasBlight(PriceOfRegrets.ID)
                && !blacklistedMonsters.contains(id)) {
            __instance.maxHealth = (int)((float)__instance.maxHealth * RustedLocket.HEALTH_BOOST_RATIO);
            __instance.currentHealth = __instance.maxHealth;
        }
    }
}