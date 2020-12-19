package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.InfectionPower;

import static theRepent.RepentMod.makeCardPath;

public class Virulence extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Virulence.class.getSimpleName());
    public static final String IMG = makeCardPath("Virulence.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    public static final String CONDITIONAL_POWER_ID = InfectionPower.POWER_ID;

    private static final int COST = 1;
    private static final int EXTRA_INFECTION = 0;
    private static final int UPGRADE_PLUS_INFECTION = 4;

    // /STAT DECLARATION/


    public Virulence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        magicNumber = baseMagicNumber = EXTRA_INFECTION;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int infectionGain = m.getPower(InfectionPower.POWER_ID).amount / 2;
        addToBot(new ReducePowerAction(m, p, InfectionPower.POWER_ID, infectionGain));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster != m) {
                addToBot(new ApplyPowerAction(monster, p, new InfectionPower(m, p, infectionGain)));
            }
        }
        if (upgraded) {
            addToBot(new ApplyPowerAction(m, p, new InfectionPower(m, p, magicNumber)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_INFECTION);
            initializeDescription();
        }
    }
}
