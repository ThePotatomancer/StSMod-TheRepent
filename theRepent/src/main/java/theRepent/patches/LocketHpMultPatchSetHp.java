package theRepent.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.powers.AcidPower;
import theRepent.powers.BleedPower;
import theRepent.powers.RedemptionPower;
import theRepent.relics.RustedLocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "setHp",
        paramtypez = {
                int.class,
                int.class
        }
)
public class LocketHpMultPatchSetHp {
    private static final List<String> blacklistedMonsters = Arrays.asList(Mugger.ID);

    @SpirePostfixPatch
    public static void setHp(AbstractMonster __instance, int minHp, int maxHp) {
        if (AbstractDungeon.player.hasRelic(RustedLocket.ID) && !blacklistedMonsters.contains(__instance.id)) {
            __instance.currentHealth = (int)((float)__instance.maxHealth * RustedLocket.HEALTH_BOOST_RATIO);
            __instance.maxHealth = __instance.currentHealth;
        }
    }
}