package theRepent.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theRepent.RepentMod.makeID;

public class DefaultCustomVariable extends DynamicVariable
{   // Custom Dynamic Variables are what you do if you need your card text to display a cool, changing number that the base game doesn't provide.
    // If the !D! and !B! (for Damage and Block) etc. are not enough for you, this is how you make your own one. It Changes In Real Time!
  
    
    // This is what you type in your card string to make the variable show up. Remember to encase it in "!"'s in the json!
    @Override
    public String key()
    {
        return makeID("ENERGY_DAMAGE");
    }

    // Checks whether the current value is different than the base one. 
    @Override
    public boolean isModified(AbstractCard card)
    {
        return card.isDamageModified;
    }
   
    // The value the variable should display.
    @Override
    public int value(AbstractCard card)
    {
        return card.damage * EnergyPanel.getCurrentEnergy();
    }
    
    // The baseValue the variable should display.
    @Override
    public int baseValue(AbstractCard card)
    {   
        return card.baseDamage * EnergyPanel.getCurrentEnergy();
    }
    
    // If the card has it's damage upgraded, this variable will glow green on the upgrade selection screen as well.
    @Override
    public boolean upgraded(AbstractCard card)
    {               
       return card.upgradedDamage;
    }
}