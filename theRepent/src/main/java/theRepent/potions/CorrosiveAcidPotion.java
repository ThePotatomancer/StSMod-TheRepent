package theRepent.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theRepent.RepentMod;
import theRepent.actions.SetMaxHealthAction;
import theRepent.powers.AcidPower;

public class CorrosiveAcidPotion extends AbstractPotion {


    public static final String POTION_ID = RepentMod.makeID("PlaceholderPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public CorrosiveAcidPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.JAR, PotionColor.POISON);

        potency = getPotency();

        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];

        isThrown = false;
        
        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));
        
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer player = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (final AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(monster, player, new AcidPower(monster, player, potency)));
                }
            }
         }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new CorrosiveAcidPotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 8;
    }

    public void upgradePotion()
    {
      potency += 4;
      tips.clear();
      tips.add(new PowerTip(name, description));
    }
}
