package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;

import static theRepent.RepentMod.makeCardPath;

public class MedicalPoisoning extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(MedicalPoisoning.class.getSimpleName());
    public static final String IMG = makeCardPath("MedicalPoisoning.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int HEAL = 5;
    private static final int UPGRADE_PLUS_HEAL = 3;

    private static final int POISON = 5;
    private static final int UPGRADE_PLUS_POISON = 5;

    // /STAT DECLARATION/


    public MedicalPoisoning() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEAL;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = POISON;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(m, p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m ,p, defaultSecondMagicNumber)));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_POISON);
            initializeDescription();
        }
    }
}
