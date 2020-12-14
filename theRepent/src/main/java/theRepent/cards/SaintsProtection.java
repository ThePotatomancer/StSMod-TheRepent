package theRepent.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.VirtuePower;

import static theRepent.RepentMod.makeCardPath;

public class SaintsProtection extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(SaintsProtection.class.getSimpleName());
    public static final String IMG = makeCardPath("SaintsProtection.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    // /STAT DECLARATION/


    public SaintsProtection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = p.getPower(VirtuePower.POWER_ID).amount;
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(p, p, VirtuePower.POWER_ID));
        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(p, p, amount));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(VirtuePower.POWER_ID)) {
            return super.canUse(p, m);
        }
        else {
            return false;
        }
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
