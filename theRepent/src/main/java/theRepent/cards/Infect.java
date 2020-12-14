package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.BleedPower;
import theRepent.powers.InfectionPower;

import static theRepent.RepentMod.makeCardPath;

public class Infect extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Infect.class.getSimpleName());
    public static final String IMG = makeCardPath("Infect.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/


    public Infect() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower bleed = m.getPower(BleedPower.POWER_ID);
        int loss = (int) Math.ceil(bleed.amount / 3.0);
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new InfectionPower(m, p, loss)));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(m, p, bleed, loss));
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if (m != null && m.hasPower(BleedPower.POWER_ID)) {
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
        boolean anyMonsterHasBleed = false;
        for(final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(BleedPower.POWER_ID)) {
                anyMonsterHasBleed = true;
                break;
            }
        }

        if (anyMonsterHasBleed) {
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
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
