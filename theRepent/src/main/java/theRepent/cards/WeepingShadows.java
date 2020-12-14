package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.WeepingShadowsPower;

import static theRepent.RepentMod.makeCardPath;

public class WeepingShadows extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(WeepingShadows.class.getSimpleName());
    public static final String IMG = makeCardPath("WeepingShadows.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK_PER_DEBUFF = 2;
    private static final int UPGRADE_PLUS_BLOCK_PER_DEBUFF = 0;

    // /STAT DECLARATION/


    public WeepingShadows() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLOCK_PER_DEBUFF;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new WeepingShadowsPower(p, p, magicNumber)));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BLOCK_PER_DEBUFF);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
