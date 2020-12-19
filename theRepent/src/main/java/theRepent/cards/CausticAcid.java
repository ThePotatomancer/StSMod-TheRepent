package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.AcidPower;
import theRepent.powers.CausticAcidPower;

import static theRepent.RepentMod.makeCardPath;

public class CausticAcid extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(CausticAcid.class.getSimpleName());
    public static final String IMG = makeCardPath("CausticAcid.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    public static final String CONDITIONAL_POWER_ID = AcidPower.POWER_ID;

    private static final int COST = 1;

    private static final int ACID_POTENCY = 1;
    private static final int UPGRADE_PLUS_ACID_POTENCY = 1;

    // /STAT DECLARATION/


    public CausticAcid() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        baseMagicNumber = magicNumber = ACID_POTENCY;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new CausticAcidPower(m, p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ACID_POTENCY);
            initializeDescription();
        }
    }
}
