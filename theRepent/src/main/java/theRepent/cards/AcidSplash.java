package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.AcidPower;

import static theRepent.RepentMod.makeCardPath;

public class AcidSplash extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(AcidSplash.class.getSimpleName());
    public static final String IMG = makeCardPath("AcidSplash.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int ACID = 4;
    private static final int UPGRADE_PLUS_ACID = 3;

    // /STAT DECLARATION/


    public AcidSplash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ACID;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(monster, p, new AcidPower(monster, p, magicNumber)));
            }
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ACID);
            initializeDescription();
        }
    }
}
