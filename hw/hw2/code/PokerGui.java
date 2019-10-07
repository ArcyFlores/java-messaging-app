/*
 * Created on Jul 19, 2004
 *
 */

/**
 * @author Owen Astrachan
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class PokerGui extends JFrame{
    private String[] rankPrefix = {
            "*", "a", "2", "3","4","5","6","7","8","9","t","j","q","k"      
        };
    private String[] suitPrefix = {
            "s", "h", "d", "c"
        };
    private static final String BLANK = "images/b.gif";
    private static final String JOKER = "images/j.gif";
    private static final int COUNT = 5;
    private JCheckBox[] myHolds;
    private CardPanel[] myCards;
    private ImageIcon myCardBack;
    private ImageIcon myJoker;
    
    private Map myImageMap;
    private Map myGrayImageMap;
    
    public PokerGui()  {
        setTitle("Compsci Poker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
       
        loadImages();
        
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout());
		panel.add(makeCardPanel(), BorderLayout.CENTER);
		panel.add(makeDealPanel(), BorderLayout.NORTH);
		pack();
		//setSize(400,400);
		setVisible(true);
    }
    
    private void clear(){
        for(int k=0; k < COUNT; k++){
            myHolds[k].setSelected(false);
            myCards[k].clear();
        }
    }
    public JPanel makeCardPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        myHolds = new JCheckBox[COUNT];
        myCards = new CardPanel[COUNT];
        JPanel cp = new JPanel(new GridLayout(1,5));
        JPanel hp = new JPanel(new GridLayout(1,5));
        for(int k=0; k < COUNT; k++){
            myHolds[k] = new JCheckBox("hold");
            hp.add(myHolds[k]);
            myCards[k] = new CardPanel();
            cp.add(myCards[k]);
        }
        panel.add(cp,BorderLayout.CENTER);
        //panel.add(hp,BorderLayout.SOUTH);
        return panel;
        
    }
    
    public JPanel makeDealPanel(){
        JPanel p = new JPanel();
        JButton b = new JButton("Deal");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                deal();
            }
        });
        JButton c = new JButton("Clear");
        c.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        p.add(b);
        p.add(c);
        return p;
    }
    
   
    private String getName(ICard card){
        String ret = "images/";
        return ret + rankPrefix[card.getRank()]+
               suitPrefix[card.getSuit()]+".gif";
    }
    
    private void loadImages(){
        myImageMap = new TreeMap();
        myGrayImageMap = new TreeMap();
        Deck d = new Deck();
        while (d.hasNext()){
            ICard c = (ICard) d.next();
            String name =getName(c);
            Image im;
            try {
                im = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(name)));
                ImageIcon icon = new ImageIcon(im);
                myImageMap.put(c,icon);
                im = GrayFilter.createDisabledImage(im);
                if (im == null){
                    System.out.println("gray image is null "+name);
                }
                myGrayImageMap.put(c,new ImageIcon(im));
            } catch (IOException e) {
                System.out.println("trouble loading "+name);
                e.printStackTrace();
            }
        }
        try {
            Image im = ImageIO.read(
                    new BufferedInputStream(getClass().getResourceAsStream(BLANK)));
            myCardBack = new ImageIcon(im);
            im = ImageIO.read(
                    new BufferedInputStream(getClass().getResourceAsStream(JOKER)));
            myJoker = new ImageIcon(im);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void deal(){
        Deck d = new Deck();
        for(int k=0; k < COUNT; k++){
            if (! myCards[k].isFrozen()){
                myCards[k].setCard((ICard)d.next());
            }
        }
        repaint();
    }
    
    public static void main(String[] args) {
        PokerGui gui = new PokerGui();
    }
    
    //public class CardPanel extends JPanel implements MouseListener{
    public class CardPanel extends JToggleButton implements ActionListener {
        private ICard myCard;
        private boolean myFrozen;
        private Border ourBorder;
        
        public CardPanel(){
            super(myJoker);
            myFrozen = false;
            //setFocusPainted(true);

            Border blackline = BorderFactory.createLineBorder(Color.black);
            TitledBorder out = BorderFactory.createTitledBorder(blackline,"hold");
            out.setTitleJustification(TitledBorder.LEFT);
            
            TitledBorder in = BorderFactory.createTitledBorder(blackline,"hold");
            in.setTitleJustification(TitledBorder.RIGHT);
            in.setTitlePosition(TitledBorder.BOTTOM);
            ourBorder = BorderFactory.createCompoundBorder(out,in);
            // setBorder(out);
            setBorderPainted(false);
            addActionListener(this);

        }
        
        public boolean isFrozen(){
            return myFrozen;
        }
        public void clear(){
            myFrozen = false;
            setBorderPainted(false);
            setIcon(myJoker);
        }
        
        
        public void setCard(ICard c){
            myCard = c;
            setIcon((ImageIcon) myImageMap.get(myCard));    
        }
        
        public void actionPerformed(ActionEvent e) {
            myFrozen = ! myFrozen;
            setBorderPainted(myFrozen);
            if (getIcon() != myJoker){
                Map m = isFrozen() ? myGrayImageMap : myImageMap;
                setIcon((ImageIcon) m.get(myCard));
            }
        }
    }
}
