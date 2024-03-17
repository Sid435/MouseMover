import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class MouseMoverApp {
    private Robot robot;
    private Timer timer;
    private int interval;
    private boolean running;
    private JLabel timeLabel;
    private JSpinner intervalSpinner;
    private JButton startButton;
    private JButton stopButton;
    public MouseMoverApp() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            System.exit(1);
        }
 
        JFrame frame = new JFrame("Mouse Mover");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        SpinnerModel intervalModel = new SpinnerNumberModel(1, 1, 60, 1);
        intervalSpinner = new JSpinner(intervalModel);
        intervalSpinner.addChangeListener(e -> interval = (int) intervalSpinner.getValue() * 60000); // Convert minutes to milliseconds

        startButton = new JButton("Start");
        startButton.addActionListener(new StartActionListener());

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new StopActionListener());

        timeLabel = new JLabel("Ready to start");

        panel.add(new JLabel("Interval (minutes):"), BorderLayout.WEST);
        panel.add(intervalSpinner, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.EAST);
        panel.add(stopButton, BorderLayout.SOUTH);
        panel.add(timeLabel, BorderLayout.NORTH);
        panel.setBorder(new EmptyBorder(5, 5, 5 ,5));

        frame.setSize(350, 110);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void startTimer() {
        running = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        interval = (int) intervalSpinner.getValue() * 60000;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeLabel.setText("Running...");
            }
        }, 0, interval);

        new Thread(() -> {
            while (running) {
                moveMouseRandomly();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    break;
                }
            }

            stopTimer();
        }).start();
    }

    private void stopTimer() {
        running = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void moveMouseRandomly() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Random random = new Random();

        int x = random.nextInt((int) screenSize.getWidth());
        int y = random.nextInt((int) screenSize.getHeight());

        robot.mouseMove(x, y);

        System.out.println("moved");
    }

    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLabel.setText("Running...");
            startTimer();
        }
    }

    private class StopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLabel.setText("Paused");
            stopTimer();
        }
    }

}

public class MouseMover {
    private static void showSplashScreen() {
        JFrame splashFrame = new JFrame();
        splashFrame.setUndecorated(true);
        splashFrame.setSize(480, 270);
        splashFrame.setLocationRelativeTo(null);

        JPanel splashPanel = new JPanel(new BorderLayout());
        JLabel splashLabel = new JLabel(new ImageIcon("/Users/siddharthjha/Downloads/Mouse Jiggler/spImg.jpg"));
        splashPanel.add(splashLabel, BorderLayout.CENTER);


        splashFrame.setContentPane(splashPanel);
        splashFrame.setVisible(true);

        javax.swing.Timer timer = new javax.swing.Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splashFrame.dispose();
                new MouseMoverApp();
            }
        });
        timer.setRepeats(false); // Make the timer run only once
        timer.start();
    }

    private static void showWarning() {
        JOptionPane.showMessageDialog(
                null,
                "JDK level 1.8 or above required",
                "Cannot run",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String javaVersionString = System.getProperty("java.version");
            System.out.println(javaVersionString);


            if (javaVersionString.compareTo("1.8") > 0) {
                showSplashScreen();
            } else {
                showWarning();
            }
        });
    }
}