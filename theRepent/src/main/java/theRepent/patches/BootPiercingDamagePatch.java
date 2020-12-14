package theRepent.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.Boot;

@SpirePatch(
        clz = Boot.class,
        method = "onAttackToChangeDamage",
        paramtypez = {
                DamageInfo.class,
                int.class
        }
)
public class BootPiercingDamagePatch {
    @SpirePrefixPatch
    public static SpireReturn<Integer> onAttackToChangeDamage(
        Boot __instance, DamageInfo info, int damageAmount) {
            if (info.type == ExtendedDamageTypes.PIERCING) {
                return SpireReturn.Return(damageAmount);
            }
            else {
                return SpireReturn.Continue();
            }
    }
}
