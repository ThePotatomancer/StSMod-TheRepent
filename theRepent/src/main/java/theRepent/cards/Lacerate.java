package theRepent.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.BleedPower;

import static theRepent.RepentMod.makeCardPath;

public class Lacerate extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Lacerate.class.getSimpleName());
    public static final String IMG = makeCardPath("Lacerate.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int BLEED = 6;
    private static final int UPGRADE_PLUS_BLEED = 4;

    // /STAT DECLARATION/


    public Lacerate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = BLEED;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new BleedPower(m, p, magicNumber), magicNumber));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_BLEED);
            initializeDescription();
        }
    }
}
