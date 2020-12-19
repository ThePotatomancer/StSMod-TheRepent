package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnvenomPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.DipVenomPower;

import static theRepent.RepentMod.makeCardPath;

public class DipVenom extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(DipVenom.class.getSimpleName());
    public static final String IMG = makeCardPath("DipVenom.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int ENVENOM = 2;
    private static final int UPGRADE_PLUS_ENVENOM = 1;

    // /STAT DECLARATION/

    public DipVenom() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENVENOM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new EnvenomPower(p, magicNumber)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new DipVenomPower(p, p, magicNumber)));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ENVENOM);
            initializeDescription();
        }
    }
}
