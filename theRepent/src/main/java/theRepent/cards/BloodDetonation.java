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

public class BloodDetonation extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(BloodDetonation.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodDetonation.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    private static final String CONDITIONAL_POWER_ID = BleedPower.POWER_ID;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    // /STAT DECLARATION/


    public BloodDetonation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower power = m.getPower(BleedPower.POWER_ID);
        int amount = power.amount / 2;

        if (upgraded) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, amount, damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(m, p, BleedPower.POWER_ID, amount));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}
