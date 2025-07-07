import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGUI extends JFrame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 5;
    private int score = 0;

    private JLabel instructionLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;
    private JButton playAgainButton;

    public NumberGuessingGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        Font font = new Font("Arial", Font.BOLD, 16);

        instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setFont(font);
        add(instructionLabel);

        inputField = new JTextField();
        add(inputField);

        guessButton = new JButton("Guess!");
        guessButton.setBackground(new Color(135, 206, 235));
        add(guessButton);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(feedbackLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(attemptsLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setVisible(false);
        playAgainButton.setBackground(new Color(144, 238, 144));
        add(playAgainButton);

        // Listeners
        guessButton.addActionListener(e -> handleGuess());
        playAgainButton.addActionListener(e -> resetGame());

        resetGame(); // Initial setup

        setVisible(true);
    }

    private void handleGuess() {
        String input = inputField.getText().trim();
        int userGuess;

        try {
            userGuess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            feedbackLabel.setText(" Please enter a valid number!");
            return;
        }

        attempts--;

        if (userGuess == numberToGuess) {
            int points = (maxAttempts - attempts) * 10;
            score += points;
            feedbackLabel.setText(" Correct! You've earned " + points + " points.");
            scoreLabel.setText("Score: " + score);
            inputField.setEnabled(false);
            guessButton.setEnabled(false);
            playAgainButton.setVisible(true);
        } else if (userGuess < numberToGuess) {
            feedbackLabel.setText("🔼 Too low!");
        } else {
            feedbackLabel.setText("🔽 Too high!");
        }

        attemptsLabel.setText("Attempts left: " + attempts);

        if (attempts == 0 && userGuess != numberToGuess) {
            feedbackLabel.setText(" Out of attempts! Number was: " + numberToGuess);
            inputField.setEnabled(false);
            guessButton.setEnabled(false);
            playAgainButton.setVisible(true);
        }

        inputField.setText("");
    }

    private void resetGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attempts = maxAttempts;
        inputField.setEnabled(true);
        guessButton.setEnabled(true);
        inputField.setText("");
        feedbackLabel.setText(" ");
        attemptsLabel.setText("Attempts left: " + maxAttempts);
        playAgainButton.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGUI::new);
    }
}
