package theRepent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theRepent.powers.VirtuePower;

public class PurificationAction extends AbstractGameAction {
    private final AbstractPlayer player;
    private final int maxSelection;
    private final int virtueCost;
    private final int blockGain;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public PurificationAction(AbstractPlayer player, int maxSelection, int virtueCost, int blockGain) {
        this.player = player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.maxSelection = maxSelection;
        this.virtueCost = virtueCost;
        this.blockGain = blockGain;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[1], maxSelection, true, true);
            addToBot(new WaitAction(0.25F));
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    addToTop(new DrawCardAction(player, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
                    for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                        AbstractDungeon.player.hand.moveToExhaustPile(card);
                        addToTop(new GainBlockAction(player, blockGain));
                        addToTop(new ReducePowerAction(player, player, VirtuePower.POWER_ID, virtueCost));
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("GamblingChipAction");
        TEXT = uiStrings.TEXT;
    }
}
