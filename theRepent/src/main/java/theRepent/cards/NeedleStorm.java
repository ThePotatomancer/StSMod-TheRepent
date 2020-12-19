package theRepent.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.actions.PiercingDamageAction;
import theRepent.characters.TheRepent;

import static theRepent.RepentMod.makeCardPath;

public class NeedleStorm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(NeedleStorm.class.getSimpleName());
    public static final String IMG = makeCardPath("NeedleStorm.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 2;

    private static final int DAMAGE = 1;
    private static final int ATTACKS = 5;
    private static final int UPGRADE_PLUS_ATTACKS = 2;

    // /STAT DECLARATION/


    public NeedleStorm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ATTACKS;
        this.isMultiDamage = true;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            AbstractDungeon.actionManager.addToBottom(
                    new PiercingDamageAction(monster, p, DAMAGE, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ATTACKS);
            initializeDescription();
        }
    }
}
