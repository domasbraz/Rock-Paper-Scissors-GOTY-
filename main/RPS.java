package RPS;

import javax.swing.JOptionPane;

import RPS.Dependencies.Link;
import RPS.Dependencies.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RPS 
{
    static int wins = 0;
    static int loses = 0;
    static int ties = 0;
    static String playerChoice, systemChoice;
    static Link myNodes = new Link();
    static String prevResult;

    static void setNodes()
    {
        Node rock = myNodes.createNode("rock");
        Node paper = myNodes.createNode("paper");
        Node scissors = myNodes.createNode("scissors");

        myNodes.linkNextNode(paper, rock);
        myNodes.linkNextNode(rock, scissors);
        myNodes.linkNextNode(scissors, paper);
    }

    static String systemChooseRandom()
    {
        ArrayList<String> options = new ArrayList<>(Arrays.asList("rock", "paper", "scissors"));
        Collections.shuffle(options);

        return options.get(0);
    }

    static String pickAChoice()
    {
        String[] options = {"Rock", "Paper", "Scissors"};

        int playerChoiceIndex = JOptionPane.showOptionDialog(
            null, 
            null, 
            "Pick one!", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            options, 
            null
        );

        if (playerChoiceIndex < 0)
        {
            System.exit(0);
        }
        String playerChoice = options[playerChoiceIndex].toLowerCase();

        return playerChoice;
    }

    static void showResult(boolean firstInstance)
    {
        if (firstInstance)
        {
            Node playerNode = myNodes.findNode(playerChoice);
            Node systemNode = myNodes.findNode(systemChoice);
            String result;

            if (playerNode.name.equals(systemNode.name))
            {
                result = "You Tied!";
                ties++;
            }
            else if (playerNode.next.name.equals(systemNode.name))
            {
                result = "You Win!";
                wins++;
            }
            else
            {
                result = "You Lose!";
                loses++;
            }

            prevResult = 
                "<html><body><h1>" + result + "</h1>" +
                "<p>You chose: " + playerChoice + "</p>" +
                "<p>System chose: " + systemChoice + "</p>" +
                "</body></html>"; 
        }

        JOptionPane.showMessageDialog(
            null, 
            prevResult, 
            "Result", 
            JOptionPane.PLAIN_MESSAGE
        );

        playAgain();
    }

    static int getTotalMatches()
    {
        return wins + loses + ties;
    }

    static double calcWinRate()
    {
        BigDecimal w = new BigDecimal(Integer.toString(wins));
        BigDecimal t = new BigDecimal(Integer.toString(ties));

        int total = getTotalMatches();

        w = w.add(t.divide(new BigDecimal("2")));
        w = w.divide(new BigDecimal(Integer.toString(total)));
        w = w.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);

        double result = w.doubleValue();

        return result;
    }
    
    static void showStats()
    {
        double winRate = calcWinRate();

        String colour = "orange";

        if (winRate > 45.00 && winRate < 55.00)
        {
            //ultra minor improvement to runtime
        }
        else if (winRate >= 55.00)
        {
            colour = "green";
        }
        else
        {
            colour = "red";
        }

        JOptionPane.showMessageDialog(
            null,
            "<html><body><center>" + 
            "<h1>Your Statistics</h1>" +
            "<p><b>Total Games: </b>" + getTotalMatches() + "</p>" +
            "<p><b style='color:green'>Wins: </b>" + wins + "</p>" +
            "<p><b style='color:red'>Loses: </b>" + loses + "</p>" +
            "<p><b style='color:orange'>Ties: </b>" + ties + "</p>" +
            "<br>" + 
            "<p><b>Win Rate: </b><b style='color:" + colour + "'>" + winRate + "%</b></p>" +
            "</center></body></html>", 
            "Statistics", 
            JOptionPane.PLAIN_MESSAGE
        );

        playAgain();
    }

    static void playAgain()
    {
        String[] options = {"Yes", "No", "Back", "Show Stats"};

        int playerChoiceIndex = JOptionPane.showOptionDialog(
            null, 
            "Play Again?", 
            "Replay?", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            options, 
            null
        );

        if (playerChoiceIndex < 0 || playerChoiceIndex == 1)
        {
            System.exit(0);
        }
        else if (playerChoiceIndex == 0)
        {
            play();
        }
        else if (playerChoiceIndex == 2)
        {
            showResult(false);
        }
        else
        {
            showStats();
        }

    }


    static void play()
    {
        playerChoice = pickAChoice();

        systemChoice = systemChooseRandom();

        showResult(true);
    }

    public static void main(String[] args) 
    {
        setNodes();

        play();

        //JOptionPane.showMessageDialog(null, myNodes.returnNodeList(), "Nodes", JOptionPane.PLAIN_MESSAGE);

    }    
}
