package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.BleedPower;

import static theRepent.RepentMod.makeCardPath;

public class BloodDetonation extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(BloodDetonation.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodDetonation.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    // /STAT DECLARATION/


    public BloodDetonation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower power = m.getPower(BleedPower.POWER_ID);
        int amount = power.amount / 2;
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, amount, damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(m, p, BleedPower.POWER_ID, amount));
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
