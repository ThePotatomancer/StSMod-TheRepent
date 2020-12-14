package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;

import static theRepent.RepentMod.makeCardPath;

public class DrawDarts extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(DrawDarts.class.getSimpleName());
    public static final String IMG = makeCardPath("DrawDarts.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int DARTS = 2;
    private static final int UPGRADE_PLUS_DARTS = 1;

    // /STAT DECLARATION/


    public DrawDarts() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DARTS;
        this.cardsToPreview = new Dart();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard dartCard = new Dart();
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(1));
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInHandAction(dartCard, magicNumber));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DARTS);
            initializeDescription();
        }
    }
}
