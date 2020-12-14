package theRepent.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.actions.TriggerPowerAction;
import theRepent.characters.TheRepent;
import theRepent.powers.BleedPower;

import java.util.Collections;

import static theRepent.RepentMod.makeCardPath;

public class Bleedout extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Bleedout.class.getSimpleName());
    public static final String IMG = makeCardPath("Bleedout.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/


    public Bleedout() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new TriggerPowerAction(m, Collections.singletonList(BleedPower.POWER_ID)));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
