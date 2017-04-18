package swing;

import java.awt.*;
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
public class BaseballGame {
    private static String IMG = "images/";
    static String[] TEAMS = { "Blue Jays", "Yankees" };

    private enum Pitches {
        CURVE, SLIDER, FASTBALL, CHANGE_UP, SPLIT_FINGER;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            System.exit(-1);
        }
        BaseballGame b = new BaseballGame();
    }

    Random random = new Random();
    JFrame frame = new JFrame();
    Container field;
    
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

    public BaseballGame() {
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

        field = frame.getContentPane();
        field.setLayout(new BorderLayout());
        field.add(nobase);

        JPanel teamRight = new JPanel();
        teamRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel rightLabel = new JLabel("Team 2");
        rightLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        teamRight.add(rightLabel);

        JPanel teamLeft = new JPanel();
        teamLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel leftLabel = new JLabel("Team 1");
        leftLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        teamLeft.add(leftLabel);
        teamLeft.add(new JLabel("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "));
        teamLeft.add(teamRight);
        field.add(teamLeft, BorderLayout.SOUTH);

        frame.setTitle("BaseballGame");
//        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        showPitchesDialog();
    }

    private void pitch(int expectedPitch) {
        int pitch = random.nextInt(Pitches.values().length);
        System.out.println(pitch);
        int hitAction = random.nextInt(6);
        if (pitch == expectedPitch) {
            switch (hitAction) {
                case 0:
                    showBatterResult("HOMERUN!");
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
                    showField();
                    break;
                case 1:
                    showBatterResult("Single");
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
                    showField();
                    break;
                case 2:
                    showBatterResult("Double");
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
                    showField();
                    break;
                case 3:
                    showBatterResult("Triple");
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
                    showField();
                    break;
                default:
                    showBatterResult("Out!");
                    outs++;
                    break;
            }
        } else {
            switch (hitAction) {
                case 3:
                case 4:
                    showBatterResult("Single");
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
                    showField();
                    break;
                default:
                    showBatterResult("Out!");
                    outs++;
                    break;
            }
        }
        showScore();
        if (outs == 3) {
            if (sideBatting == 1) {
                inning++;
            }
            if (inning < 9) {
                sideBatting = 1 - sideBatting;
                showBatterResult("Player " + sideBatting);
                outs = 0;
                first = second = third = false;
            }
            showField();
        }
    }

    private void showField() {
        field.removeAll();
        if (first && second && third) {
            field.add(base123);
        } else if (first && second && !third) {
            field.add(base12);
        } else if (first && third && !second) {
            field.add(base13);
        } else if (second && third && !first) {
            field.add(base23);
        } else if (third && !second && !first) {
            field.add(base3);
        } else if (second && !third && !first) {
            field.add(base2);
        } else if (first && !second && !third) {
            field.add(base1);
        } else if (!first && !second && !third) {
            field.add(nobase);
        }
        repaint();
    }

    private void repaint() {
        frame.repaint();
        frame.validate();
    }

    JDialog scoreBoard;
    JLabel score;

    private void showScore() {
        if (scoreBoard == null) {
            scoreBoard = new JDialog();
            scoreBoard.setLayout(new FlowLayout());
            scoreBoard.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
            score = new JLabel();
            score.setFont(new Font("Serif", Font.PLAIN, 25));
            dialogPanel.add(score);
            scoreBoard.add(dialogPanel);
            scoreBoard.pack();
            scoreBoard.setVisible(true);
        }
        score.setText(TEAMS[0] + " " + runs[0] + "-" + TEAMS[1] + " " + runs[1]);
    }

    JDialog hitAction;
    JLabel hitLabel;

    private void showBatterResult(String phrase) {
        if (hitAction == null) {
            hitAction = new JDialog();
            hitAction.setLayout(new FlowLayout());
            hitLabel = new JLabel(phrase);
            hitLabel.setFont(new Font("Serif", Font.PLAIN, 250));
            hitAction.add(hitLabel);
            hitAction.pack();
            hitAction.setVisible(true);
            hitAction.setAlwaysOnTop(true);
        }
        hitLabel.setText(phrase);
    }

    JDialog pitchDialog;

    private void showPitchesDialog() {
        if (pitchDialog == null) {
            pitchDialog = new JDialog();

            pitchDialog.setLayout(new FlowLayout());
            pitchDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
                    pitch(4);
                }
            });
            JLabel layoutLabel = new JLabel("What pitch do you think is going to be thrown?");
            layoutLabel.setFont(new Font("Serif", Font.PLAIN, 20));

            dialogPanel.add(layoutLabel);
            dialogPanel.add(curveballPanel);
            dialogPanel.add(sliderPanel);
            dialogPanel.add(fastballPanel);
            dialogPanel.add(changeupPanel);
            dialogPanel.add(splitfingerPanel);

            layoutPanel.add(dialogPanel);

            pitchDialog.add(layoutPanel);
//            pitchDialog.setSize(500, 500);
            pitchDialog.pack();
            pitchDialog.setVisible(true);
        }
    }
}