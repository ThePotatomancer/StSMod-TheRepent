package theRepent.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.AcidPower;
import theRepent.powers.BleedPower;
import theRepent.powers.InfectionPower;

import static theRepent.RepentMod.makeCardPath;

public class BladeOfRuin extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(BladeOfRuin.class.getSimpleName());
    public static final String IMG = makeCardPath("BladeOfRuin.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int POTENCY = 5;
    private static final int UPGRADE_PLUS_POTENCY = 3;


    // /STAT DECLARATION/


    public BladeOfRuin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = POTENCY;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = POTENCY * 3;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m, p, new BleedPower(m, p, defaultSecondMagicNumber)));
        addToBot(new ApplyPowerAction(m, p, new AcidPower(m, p, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new InfectionPower(m, p, magicNumber)));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_POTENCY * 3);
            upgradeMagicNumber(UPGRADE_PLUS_POTENCY);
            initializeDescription();
        }
    }
}
