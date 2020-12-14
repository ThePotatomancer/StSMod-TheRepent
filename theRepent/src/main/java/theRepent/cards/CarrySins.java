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

public class CarrySins extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(CarrySins.class.getSimpleName());
    public static final String IMG = makeCardPath("CarrySins.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    // /STAT DECLARATION/


    public CarrySins() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
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

    public boolean cardPlayable(AbstractMonster m) {
        if (m != null && m.hasPower(SinPower.POWER_ID)) {
            return super.cardPlayable(m);
        }
        else {
            this.cantUseMessage = null;
            return false;
        }
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean anyMonsterHasSin = false;
        for(final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(SinPower.POWER_ID)) {
                anyMonsterHasSin = true;
                break;
            }
        }

        if (anyMonsterHasSin) {
            return super.canUse(p, m);
        }
        else {
            return false;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            retain = true;
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
