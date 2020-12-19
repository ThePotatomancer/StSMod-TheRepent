package theRepent.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.actions.PurificationAction;
import theRepent.characters.TheRepent;
import theRepent.powers.VirtuePower;

import static theRepent.RepentMod.makeCardPath;

public class Purification extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(Purification.class.getSimpleName());
    public static final String IMG = makeCardPath("Purification.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    private static final String CONDITIONAL_POWER_ID = VirtuePower.POWER_ID;

    private static final int COST = 0;

    private static final int VIRTUE_COST = 7;
    private static final int UPGRADE_MINUS_VIRTUE_COST = -2;
    private static final int MIN_VIRTUE_COST = 3;

    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    // /STAT DECLARATION/


    public Purification() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        magicNumber = baseMagicNumber = VIRTUE_COST;
        baseBlock = BLOCK;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int maxExhausts = p.getPower(VirtuePower.POWER_ID).amount / magicNumber;
        addToBot(new PurificationAction(p, maxExhausts, magicNumber, block));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            if (magicNumber > MIN_VIRTUE_COST) {
                upgradeMagicNumber(UPGRADE_MINUS_VIRTUE_COST);
            }
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
