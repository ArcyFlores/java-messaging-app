
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

public class SimpleGuiTester extends JFrame{
    private String[] rankPrefix = {
            "*", "a", "2", "3","4","5","6","7","8","9","t","j","q","k"      
        };
    private String[] suitPrefix = {
            "s", "h", "d", "c"
        };
    private static final String BLANK = "images/b.gif";
    private static final String JOKER = "images/j.gif";
    private static final int HAND_SIZE = 5;
    private CardPanel[] myCards;
    private ImageIcon myCardBack;
    private ImageIcon myJoker;
    private TestPanel[] myTestPanels;
    private Map myImageMap;
    
    public SimpleGuiTester(int panels){
        setTitle("AP Card Tester");
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
        loadImages();
        
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(getTesters(panels), BorderLayout.CENTER);
        panel.add(makeDealPanel(), BorderLayout.NORTH);
        pack();
        setVisible(true);
    }
    
    public SimpleGuiTester()  {
        this(1);
    }
    
    private JScrollPane getTesters(int n){
        JPanel panel = new JPanel(new GridLayout(n,1));
        myTestPanels = new TestPanel[n];
        for(int k=0; k < n; k++){
            myTestPanels[k] = new TestPanel();
            panel.add(myTestPanels[k]);
        }
        return new JScrollPane(panel);
    }
    
    public JPanel makeDealPanel(){
        JPanel p = new JPanel();
        JButton b = new JButton("Deal");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                deal();
            }
        });
        p.add(b);
        return p;
    }
    
   
    private String getName(ICard card){
        String ret = "images/";
        return ret + rankPrefix[card.getRank()]+
               suitPrefix[card.getSuit()]+".gif";
    }
    
    private void loadImages(){
        myImageMap = new TreeMap();
        Deck d = new Deck();
        while (d.hasNext()){
            ICard c = (ICard) d.next();
            String name =getName(c);
            Image im;
            try {
                im = ImageIO.read(new BufferedInputStream(getClass().getResourceAsStream(name)));
                ImageIcon icon = new ImageIcon(im);
                myImageMap.put(c,icon);
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
        for(int k=0; k < myTestPanels.length; k++){
            myTestPanels[k].deal(d);
        }
    }
    
    public static void main(String[] args) {
        SimpleGuiTester gui = new SimpleGuiTester(4);
    }
    
    public class TestReportPanel extends JLabel{
        private static final int SIZE = 12;
        public TestReportPanel(){
            super("result");
            setFont(new Font("Monospaced", Font.BOLD,14));
            Border blackline = BorderFactory.createLineBorder(Color.black);
            TitledBorder tb = BorderFactory.createTitledBorder(blackline,"results");
            tb.setTitleJustification(TitledBorder.LEFT);
            this.setBorder(tb);
        }
        public void setText(String s){
            int first = (SIZE - s.length())/2;
            for(int k=0; k < first; k++){
                s = " " + s;
            }
            while (s.length() != SIZE){
                s += " ";
            }
            super.setText(s);
        }
    }
    
    public class CardPanel extends JButton  {
        private ICard myCard;
        
        public CardPanel(){
            super(myJoker);
        }

        public void setCard(ICard c){
            myCard = c;
            setIcon((ImageIcon) myImageMap.get(myCard));    
        }
    }
    
    public class TestPanel extends JPanel{
        private CardPanel[] myCards;
        private TestReportPanel myReport;
        private SimpleHand myHand;
        
        public TestPanel(){
            super(new BorderLayout());
            myCards = new CardPanel[HAND_SIZE];
            JPanel cp = new JPanel(new GridLayout(1,5));
            for(int k=0; k < HAND_SIZE; k++){
                myCards[k] = new CardPanel();
                cp.add(myCards[k]);
            }
            myHand = new SimpleHand();
            myReport = new TestReportPanel();
            this.add(cp,BorderLayout.CENTER);
            this.add(myReport,BorderLayout.WEST);
        }
        
        public void deal(Deck d){
            ICard[] cards = new ICard[HAND_SIZE];
            for(int k=0; k < HAND_SIZE; k++){
                cards[k] = (ICard) d.next();
                myCards[k].setCard(cards[k]);
            }
            repaint();
            myReport.setText(""+myHand.allRed(cards));
        }
    }
}
