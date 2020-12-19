package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.ParalyticPoisonPower;

import static theRepent.RepentMod.makeCardPath;

public class ParalyticPoison extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(ParalyticPoison.class.getSimpleName());
    public static final String IMG = makeCardPath("ParalyticPoison.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    public static final String CONDITIONAL_POWER_ID = PoisonPower.POWER_ID;

    private static final int COST = 1;

    private static final int INITIAL_POTENCY = 1;
    private static final int UPGRADE_PLUS_INITIAL_POTENCY = 1;
    private static final int EXTRA_POISON = 0;
    private static final int UPGRADE_PLUS_EXTRA_POISON = 3;

    // /STAT DECLARATION/


    public ParalyticPoison() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        magicNumber = baseMagicNumber = INITIAL_POTENCY;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = EXTRA_POISON;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new ParalyticPoisonPower(m, p, magicNumber)));
        if (upgraded) {
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, defaultSecondMagicNumber)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_INITIAL_POTENCY);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_EXTRA_POISON);
            initializeDescription();
        }
    }
}
