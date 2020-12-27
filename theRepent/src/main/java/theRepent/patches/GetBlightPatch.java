package theRepent.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.helpers.BlightHelper;

import java.util.HashMap;

@SpirePatch(
        clz = BlightHelper.class,
        method = "getBlight",
        paramtypez = {
                String.class
        }
)
public class GetBlightPatch {
    public static HashMap<String, AbstractBlight> registeredBlightIds = new HashMap<>();

    @SpirePrefixPatch
    public static SpireReturn<AbstractBlight> getBlight(String id) {
        if (registeredBlightIds.containsKey(id)) {
            AbstractBlight blight = registeredBlightIds.get(id);
            try {
                return SpireReturn.Return(blight.getClass().newInstance());
            } catch (IllegalAccessException | InstantiationException error) {
                throw new RuntimeException("RepentMod failed to auto-generate makeCopy for blight: " + id);
            }
        }
        return SpireReturn.Continue();
    }
}
