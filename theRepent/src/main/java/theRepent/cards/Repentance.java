package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.SinPower;
import theRepent.powers.VirtuePower;

import static theRepent.RepentMod.makeCardPath;

public class Repentance extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Repentance.class.getSimpleName());
    public static final String IMG = makeCardPath("Repentance.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/


    public Repentance() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower currentSin = p.getPower(SinPower.POWER_ID);
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(p, p, SinPower.POWER_ID));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new VirtuePower(p, p, currentSin.amount)));
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
