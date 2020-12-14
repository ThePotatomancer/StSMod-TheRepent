package theRepent.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theRepent.patches.observables.HealObservable;

/*
 * You will ***NEED*** to follow the official SpirePatch documentation here as you read through this patch.
 * https://github.com/kiooeht/ModTheSpire/wiki/SpirePatch
 * https://github.com/kiooeht/ModTheSpire/wiki/Matcher
 */

@SpirePatch(
        clz = HealAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                AbstractCreature.class,
                AbstractCreature.class,
                int.class
        }
)
public class HealActionPatch {
    private static final HealObservable healObservable = new HealObservable();

    @SpirePrefixPatch
    public static void OnHealAction(
        HealAction __instance, AbstractCreature target, AbstractCreature source, int amount) {
            healObservable.notify(target, source, amount);
   }
}