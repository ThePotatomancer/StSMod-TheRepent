package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.RedemptionPower;
import theRepent.powers.SinPower;

import static theRepent.RepentMod.makeCardPath;

public class CarrySins extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(CarrySins.class.getSimpleName());
    public static final String IMG = makeCardPath("CarrySins.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    public static final String CONDITIONAL_POWER_ID = SinPower.POWER_ID;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public CarrySins() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        retain = false;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower currentTargetSin = m.getPower(SinPower.POWER_ID);
        int redemptionAmount = (int)Math.ceil(currentTargetSin.amount / 2.0);

        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(m, p, SinPower.POWER_ID));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new SinPower(p, p, currentTargetSin.amount)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new RedemptionPower(m, p, redemptionAmount))
        );
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            retain = true;
            initializeDescription();
        }
    }
}
