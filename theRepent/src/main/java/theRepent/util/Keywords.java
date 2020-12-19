package theRepent.util;
import java.util.HashMap;
import java.util.Map;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;

/**
 * helper class for keywords. contains constants and a method to get a power tip (for potions)
 * Copied from MadScienceMod
 */
public class Keywords {
    public static final String ACID = "acid";

    public static final Map<String, String> langMap = new HashMap<>();

    public static PowerTip makePowerTip(String keyword) {
        String translated = langMap.get(keyword);
        return new PowerTip(TipHelper.capitalize(translated),
                GameDictionary.keywords.get(translated));
    }
}