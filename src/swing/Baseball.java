package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

//Pitches: Curveball = 0, slider = 1, fastball = 2, changeup = 3, splitfinger = 4
public class Baseball {
    private static String IMG = "images/";

    private enum Pitches {
        CURVE, SLIDER, FASTBALL, CHANGE_UP, SPLIT_FINGER;
    }

    ;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            System.exit(-1);
        }
        Baseball b = new Baseball();
    }

    Random random = new Random();
    JFrame frame = new JFrame();

    int outs = 0;
    int[] runs = new int[2];
    int sideBatting = 0;
    int inning = 1;

    //Boolean for bases
    boolean first = false;
    boolean second = false;
    boolean third = false;

    //Load base photos
    JLabel base1 = new JLabel();
    JLabel base2 = new JLabel();
    JLabel base3 = new JLabel();
    JLabel base12 = new JLabel();
    JLabel base123 = new JLabel();
    JLabel base13 = new JLabel();
    JLabel base23 = new JLabel();

    JLabel nobase = new JLabel();
    JPanel teamRight = new JPanel();
    JPanel teamLeft = new JPanel();

    public Baseball() {
        try {
            nobase.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base0.png"))));
            base1.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base1.png"))));
            base2.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base2.png"))));
            base3.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base3.png"))));
            base12.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base12.png"))));
            base123.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base123.png"))));
            base13.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base13.png"))));
            base23.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(IMG + "base23.png"))));
        } catch (IOException x) {
            throw new RuntimeException(x);
        }
        frame.setTitle("Baseball");
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(nobase);

        teamRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel rightLabel = new JLabel("Team 2");
        rightLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        teamRight.add(rightLabel);
        teamLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel leftLabel = new JLabel("Team 1");
        leftLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        teamLeft.add(leftLabel);
        teamLeft.add(new JLabel("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "));
        teamLeft.add(teamRight);
        //frame.add(teamRight, BorderLayout.SOUTH);
        frame.add(teamLeft, BorderLayout.SOUTH);
        repaint();
        showPlayDialog();
    }

    private void pitch(int expectedPitch) {
        int pitch = random.nextInt(Pitches.values().length);
        System.out.println(pitch);
        int hitAction = random.nextInt(6);
        if (pitch == expectedPitch) {
            switch (hitAction) {
                case 0:
                    showDialog("HOMERUN!");
                    if (third) {
                        ++runs[sideBatting];
                        third = false;
                    }
                    if (second) {
                        ++runs[sideBatting];
                        second = false;
                    }
                    if (first) {
                        ++runs[sideBatting];
                        first = false;
                    }
                    ++runs[sideBatting];
                    removeAll();
                    setImage();
                    break;
                case 1:
                    showDialog("Single");
                    if (third) {
                        ++runs[sideBatting];
                        third = false;
                    }
                    if (second) {
                        second = false;
                        third = true;
                    }
                    if (first) {
                        second = true;
                    }
                    first = true;
                    removeAll();
                    setImage();
                    break;
                case 2:
                    showDialog("Double");
                    if (third) {
                        ++runs[sideBatting];
                        third = false;
                    }
                    if (second) {
                        ++runs[sideBatting];
                        second = false;
                    }
                    if (first) {
                        first = false;
                        third = true;
                    }
                    second = true;
                    removeAll();
                    setImage();
                    break;
                case 3:
                    showDialog("Triple");
                    if (third) {
                        ++runs[sideBatting];
                        third = false;
                    }
                    if (second) {
                        ++runs[sideBatting];
                        second = false;
                    }
                    if (first) {
                        ++runs[sideBatting];
                        first = false;
                    }
                    third = true;
                    removeAll();
                    setImage();
                    break;
                default:
                    showDialog("Out!");
                    outs++;
                    break;
            }
        } else {
            switch (hitAction) {
                case 3:
                case 4:
                    showDialog("Single");
                    removeAll();
                    if (third) {
                        ++runs[sideBatting];
                        third = false;
                    }
                    if (second) {
                        second = false;
                        third = true;
                    }
                    if (first) {
                        second = true;
                    }
                    first = true;
                    setImage();
                    break;
                default:
                    showDialog("Out!");
                    outs++;
                    break;
            }
        }
        if (outs == 3) {
            if (sideBatting == 1) {
                inning++;
            }
            if (inning < 9) {
                sideBatting = 1 - sideBatting;
                showDialog("Player " + sideBatting);
                outs = 0;
                first = second = third = false;
            }
            removeAll();
            setImage();
        }
        if (!(inning == 9)) {
            showPlayDialog();
        } else {
            removeAll();
            repaint();
        }
    }

    private void setImage() {
        if (first && second && third) {
            frame.add(base123);
        } else if (first && second && !third) {
            frame.add(base12);
        } else if (first && third && !second) {
            frame.add(base13);
        } else if (second && third && !first) {
            frame.add(base23);
        } else if (third && !second && !first) {
            frame.add(base3);
        } else if (second && !third && !first) {
            frame.add(base2);
        } else if (first && !second && !third) {
            frame.add(base1);
        } else if (!first && !second && !third) {
            frame.add(nobase);
        }
        repaint();
    }

    private void repaint() {
        frame.repaint();
        frame.validate();
    }

    private void removeAll() {
        frame.remove(base1);
        frame.remove(base12);
        frame.remove(base123);
        frame.remove(base13);
        frame.remove(base2);
        frame.remove(base23);
        frame.remove(base3);
        frame.remove(nobase);
    }

    private void showScore() {
        JDialog dialog = new JDialog();
        dialog.setLayout(new FlowLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        JLabel score = new JLabel();
        score.setFont(new Font("Serif", Font.PLAIN, 25));
        score.setText(runs[0] + "-" + runs[1]);
        dialogPanel.add(score);
        dialog.add(dialogPanel);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void showDialog(String phrase) {
        JDialog homerun = new JDialog();
        homerun.setLayout(new FlowLayout());
        JLabel label = new JLabel(phrase);
        label.setFont(new Font("Serif", Font.PLAIN, 250));
        homerun.add(label);
        homerun.pack();
        homerun.setVisible(true);
        homerun.setAlwaysOnTop(true);
    }

    private void showPlayDialog() {
        final JDialog dialog = new JDialog();
        dialog.setLayout(new FlowLayout());
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(new FlowLayout());
        JButton curveball = new JButton("Curveball");
        JPanel curveballPanel = new JPanel();
        curveballPanel.setLayout(new FlowLayout());
        curveballPanel.add(curveball);
        curveball.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                pitch(0);
            }

        });

        JButton slider = new JButton("Slider");
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout());
        sliderPanel.add(slider);
        slider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                pitch(1);
            }

        });

        JButton fastball = new JButton("Fastball");
        JPanel fastballPanel = new JPanel();
        fastballPanel.setLayout(new FlowLayout());
        fastballPanel.add(fastball);
        fastball.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                pitch(2);
            }

        });

        JButton changeup = new JButton("Changeup");
        JPanel changeupPanel = new JPanel();
        changeupPanel.setLayout(new FlowLayout());
        changeupPanel.add(changeup);
        changeup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                pitch(3);
            }

        });

        JButton splitfinger = new JButton("Splitfinger");
        JPanel splitfingerPanel = new JPanel();
        splitfingerPanel.setLayout(new FlowLayout());
        splitfingerPanel.add(splitfinger);
        splitfinger.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                pitch(4);
            }

        });
        JLabel layoutLabel = new JLabel("What pitch do you think is going to be thrown?");
        layoutLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel score = new JLabel();
        score.setFont(new Font("Serif", Font.PLAIN, 20));
        score.setText(runs[0] + "-" + runs[1]);
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.add(score);
        dialogPanel.add(layoutLabel);
        dialogPanel.add(scorePanel);
        dialogPanel.add(curveballPanel);
        dialogPanel.add(sliderPanel);
        dialogPanel.add(fastballPanel);
        dialogPanel.add(changeupPanel);
        dialogPanel.add(splitfingerPanel);

        layoutPanel.add(dialogPanel);

        dialog.add(layoutPanel);
        dialog.setSize(500, 500);
        dialog.setVisible(true);
    }
}
