package theRepent.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.RepentMod;
import theRepent.actions.PiercingDamageAction;
import theRepent.characters.TheRepent;
import theRepent.powers.BleedPower;

import static theRepent.RepentMod.makeCardPath;

public class Pinprick extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Pinprick.class.getSimpleName());
    public static final String IMG = makeCardPath("Pinprick.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int DAMAGE = 1;

    private static final int BLEED = 3;
    private static final int UPGRADE_PLUS_BLEED = 3;
    private static final int POISON = 2;
    private static final int UPGRADE_PLUS_POISON = 2;

    // /STAT DECLARATION/


    public Pinprick() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLEED;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = POISON;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new PiercingDamageAction(m, p, DAMAGE, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        boolean hadPoison = m.hasPower(PoisonPower.POWER_ID);
        boolean hadBleed = m.hasPower(BleedPower.POWER_ID);
        if (hadPoison) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new BleedPower(m, p, magicNumber)));
        }
        if (hadBleed) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new PoisonPower(m, p, defaultSecondMagicNumber)));
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BLEED);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_POISON);
            initializeDescription();
        }
    }
}
