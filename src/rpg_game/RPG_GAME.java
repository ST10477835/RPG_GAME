/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpg_game;

import java.util.Scanner;

/**
 *
 * @author abule
 */
public class RPG_GAME {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Application prior setup
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(10, 10, 10);
        boolean crit_guarantee = false;
        boolean TF = true;
        boolean played = false;
        

        while (TF) {
            //Application Start
            System.out.println(
                    "=".repeat(60)
                    + "\nWelcome to dungeon challengers!\n"
                    + "Would you like to "
                    + (played == true ? "play again? (y/n)\n" : "start the game? (y/n)\n")//Detects if player has played before, welcoming them if they have
                    + "=".repeat(60)
            );

            boolean invalid_input = true;//Detects what key user enters and runs code depending on that input

            while (invalid_input) {
                System.out.print(">>");
                String play_choice = scanner.next();//Accepts user input

                switch (play_choice) {
                    case "y" -> {//If user enters "y"
                        if (!played) {
                            System.out.print("Please enter you username: ");
                            player.name = scanner.next();
                            scanner.nextLine();
                        }

                        int loss_counter = 0;//Counts losses so it can end game after a certain amount of deaths/ losses
                        
                        boolean TF1 = true;
                        while (TF1) {
                            invalid_input = true;//Opens up loop for a battle after one has ended
                            Enemy enemy = enemy_creator(player.get_lvl());//Random enemy initialisation after each fight

                            System.out.print("Walking");
                            for (int i = 0; i < 3; i++) {
                                try {//To add delay to the console
                                    System.out.print(".");
                                    Thread.sleep(800);
                                } catch (InterruptedException e) {}
                            }//Walking text effect
                            
                            System.out.println();

                            System.out.printf(
                                    "You have encountered a wild, " + enemy.get_name()
                                    +"\n============================================================%n"
                                    + "%-25s %-25s%n"
                                    + "------------------------------------------------------------%n"
                                    + "%-12s %-12s | %-12s %-12s%n"
                                    + "%-12s %-12d | %-12s %-12d%n"
                                    + "%-12s %-12d | %-12s %-12d%n"
                                    + "%-12s %-12d | %-12s %-12d%n"
                                    + "============================================================%n"
                                    +"Would you like to fight it? (y/n)",
                                    "Your Stats", "Enemy Stats",
                                    "Name:", player.get_name(), "Name:", enemy.get_name(),
                                    "Level:", player.get_lvl(), "Level:", enemy.get_lvl(),
                                    "Attack:", player.get_atk(), "Attack:", enemy.get_atk(),
                                    "Health:", player.get_hp(), "Health:", enemy.get_hp()
                            );

                            while (invalid_input) {
                                
                                System.out.print(">>");
                                String fight_choice = scanner.next();//Takes in user input
                                
                                switch (fight_choice) {
                                    case "y" -> {
                                        boolean fight_TF = true;//initiates each fight loop

                                        while (fight_TF) {
                                            //Player turn
                                            
                                            //Player and enemy healthbar generation
                                            int playerHpBar = Math.max(0, Math.min(player.get_hp(), 30));
                                            int enemyHpBar = Math.max(0, Math.min(enemy.get_hp(), 30));

                                            System.out.println(
                                                    "=".repeat(60)
                                                    + "\n"
                                                    + player.name + " HP:["
                                                    + "█".repeat(playerHpBar)
                                                    + "-".repeat(30 - playerHpBar) + "]\n"
                                                    + enemy.name + " HP:["
                                                    + "█".repeat(enemyHpBar)
                                                    + "-".repeat(30 - enemyHpBar) + "]"
                                                    +"\nSelect an option\n"
                                                    + "=".repeat(60)
                                                    + "\n1) Attack"
                                                    + "\n2) Heal"
                                                    + "\n3) Run Away\n"
                                                    + "=".repeat(60)
                                            );
                                            
                                            int fight_option = scanner.nextInt();
                                            scanner.nextLine();

                                            switch (fight_option) {
                                                case 1 -> {//Player attacks with 25% chance of player missing
                                                    
                                                    int player_prob = (int) (Math.random() * 4) + 1;//generates probability of player missing
                                                    int crit_prob = (int) (Math.random() * 10) + 1;//generates probability of player landing a critical hit

                                                    if (player_prob > 1) {//75% of attacking
                                                        if (crit_prob == 1 || crit_guarantee == true) {//10% chance of hitting a crit OR enemy fails to heal
                                                            enemy.set_hp(enemy.get_hp()-player.get_atk()*2);
                                                            System.out.println("You have successfully landed a critical attack on " + enemy.name + "!\n-" + (player.get_atk() * 2) + "hp");
                                                            if (crit_guarantee == true) {//resets critical hit guarantee
                                                                crit_guarantee = false;
                                                            }
                                                        } else {//90% chance of attacking normally
                                                            enemy.set_hp(enemy.get_hp()-player.get_atk());
                                                            System.out.println("You have successfully attacked " + enemy.name + "!\n-" + player.get_atk() + "hp");
                                                        }
                                                    } else {//25% of missing
                                                        System.out.println(enemy.name + " has dodged your attack");
                                                    }
                                                    
                                                    try {//Delay between attacks
                                                        Thread.sleep(2000);
                                                    } catch (InterruptedException e) {}
                                                    
                                                    System.out.println("=".repeat(60));

                                                }case 2 -> {//heals player with 25% of failing to heal
                                                    if (player.get_hp() == 30) {//if player is already full healed skips current iteration
                                                        System.out.println("You are already fully healed!");
                                                        continue;
                                                    }

                                                    int health_prob = (int) (Math.random() * 4) + 1;//generates probability
                                                    if (health_prob > 1) {//75% chance of healing
                                                        if (player.get_hp() <= 25) {
                                                            player.set_hp(player.get_hp()+5);
                                                            System.out.println("You have successfully healed!\n+5hp");
                                                        } else if (player.get_hp()< 30) {
                                                            player.set_hp(player.get_hp()+(30 - player.get_hp()));
                                                            System.out.println("You have successfully healed!\n+" + (30 - player.get_hp()) + "hp");
                                                        }
                                                    } else {//25% chance of failing to heal
                                                        System.out.println("You have failed to heal!");
                                                    }
                                                    
                                                    try {
                                                        Thread.sleep(2000);
                                                    } catch (InterruptedException e) {}
                                                    
                                                    System.out.println("-".repeat(60));
                                                }
                                                case 3 -> {//Running away from enemy
                                                    int escape_prob = (int) (Math.random() * 2) + 1;//generates probabilty of running away
                                                    switch (escape_prob) {//50% chance to escape fight
                                                        case 1 -> {
                                                            //successfully escaped from monster
                                                            System.out.println("You have successfully escaped!");
                                                            break;
                                                        }
                                                        case 2 -> {
                                                            //unsuccessful
                                                            System.out.println("You have failed to successfully escape");
                                                        }
                                                        default->{}
                                                    }
                                                    
                                                    try {
                                                        Thread.sleep(2000);
                                                    } catch (InterruptedException e) {}
                                                    
                                                    System.out.println("-".repeat(60));
                                                }
                                                default -> {}
                                            }

                                            if (enemy.get_hp() <= 0) {//If enemy is killed
                                                player.set_lvl(player.get_lvl()+1);//increase player level by one
                                                
                                                System.out.println(
                                                        "You have beaten " + enemy.name
                                                        + "\nYou have leveled up"
                                                );
                                                
                                                try {
                                                    Thread.sleep(2000);
                                                } catch (InterruptedException e) {}
                                                
                                                System.out.println(
                                                        "=".repeat(60)
                                                        + "\n" 
                                                        + (player.get_lvl() - 1) + "-->" + player.get_lvl()
                                                );
                                                
                                                played = true;//Detects that youve already played the game
                                                invalid_input = false;//Escapes inner "invalid input" while loop
                                                
                                                System.out.println(
                                                        "=".repeat(60) 
                                                        + "\nPress enter to continue\n" 
                                                        + "=".repeat(60)
                                                );
                                                scanner.nextLine();
                                                
                                                break;//escapes "fight_TF" loop
                                            }
                                            //Enemy attack turn

                                            if (enemy.get_hp() < 5) {//enemy becomes cautious and heals
                                                //enemy heals by ten when it enters this mode with a 12,5% chance of failing to heal
                                                int heal_prob = (int) (Math.random() * 8) + 1;//Generates probability of enemy healing
                                                
                                                if (heal_prob > 1) {//87,5% chance of healing
                                                    enemy.set_hp(enemy.get_hp()+10);
                                                    System.out.println(enemy.name + " has healed!\n+10hp");
                                                } else {//12,5% chance of failing to heal
                                                    System.out.println(enemy.name + " has failed to heal! Now's your chance!");
                                                    crit_guarantee = true;//enemy is now left vulnerable and player can hit a crit
                                                }
                                            } else {//enemy choices
                                                int enemy_choice = (int) (Math.random() * 5) + 1;//Generates probability dictating the enemy's choice in moves
                                                if (enemy_choice > 1) {//80% chance of attacking
                                                    player.set_hp(player.get_hp()-enemy.get_atk());
                                                    System.out.println(enemy.name + " has attacked you!\n-" + enemy.get_atk() + "hp");
                                                } else {//20% chance of healing
                                                    enemy.set_hp(enemy.get_hp()+5);
                                                    System.out.println(enemy.name + " has healed itself!\n+5hp");
                                                }
                                            }

                                            try {
                                                Thread.sleep(2000);
                                            } catch (InterruptedException e) {}

                                            if (player.get_hp() <= 0) {//For when a player  dies
                                                System.out.println(
                                                        "=".repeat(60)
                                                        + "\nYou have lost to " + enemy.name
                                                        + "\n"
                                                        + "=".repeat(60)
                                                );
                                                
                                                fight_TF = false;
                                                loss_counter++;//increments loss counter by 1

                                                player.set_hp(10);//resets player health

                                                if (loss_counter == 5) {//when loss counter reaches 5 this means game over
                                                    System.out.println("=".repeat(60) + "\nGAME OVER\n" + "=".repeat(60));
                                                    played = true;
                                                    invalid_input = false;
                                                    TF1 = false;
                                                } else {
                                                    invalid_input = false;
                                                    System.out.println("=".repeat(60) + "\nPress enter to continue\n" + "=".repeat(60));
                                                    scanner.nextLine();
                                                }
                                            }
                                        }

                                    }
                                    case "n" -> {
                                        System.out.println("Escaped from " + enemy.name);
                                        invalid_input = false;
                                    }
                                    case "exit" -> {
                                        System.exit(0);
                                    }
                                    default -> {//User enters incorrect input
                                        System.out.println("Incorect input, please try again!");
                                    }
                                }
                            }
                        }
                    }
                    case "n" -> {//Closes program
                        invalid_input = false;
                        System.out.println("Closing game...");
                        System.exit(0);
                    }
                    case "exit" -> {
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Incorect input, please try again!");
                    }
                }
            }
        }
    }

    public static Enemy enemy_creator(int player_lvl) {//Creates an enemy and gives it random stats
        int randAtk = 5 + (int) (Math.random() * 6);  // 5..10
        int randHp = 10 + (int) (Math.random() * 6); // 10..15

        int curr_atk = randAtk * player_lvl;
        int curr_hp = randHp * player_lvl;

        if (player_lvl > 2) {
            curr_atk /= (player_lvl - 1);
            curr_hp /= (player_lvl - 1);
        }

        int lvl_prob = (int) (Math.random() * 8) + 1;
        int curr_lvl;
        switch (lvl_prob) {//should level probability for the enemy be 1 enemy will be one level stonger than the player
            case 1 -> {
                curr_lvl = player_lvl + 1;
            }
            default -> {
                curr_lvl = player_lvl;
            }
        }

        String[] enemy_names = {
            "Varkon the Cruel",
            "Zerath the Shadowed",
            "Maldrin the Savage",
            "Thyros the Unyielding",
            "Krynn the Vengeful",
            "Dargoth the Grim",
            "Fenric the Cunning",
            "Lorvath the Ruthless",
            "Sylmaris the Fierce",
            "Balthor the Merciless",
            "Vorath the Dark",
            "Gorrim the Bloody",
            "Eryndor the Wretched",
            "Xalther the Wicked",
            "Dravok the Brutal"
        };
        String name = enemy_names[(int) (Math.random() * enemy_names.length - 1) + 1];
        return new Enemy(curr_hp, curr_lvl, curr_atk, name);
    }

}

abstract class Character {//Main character super class

    public String name = "default";
    private int hp;
    private int lvl;
    private int atk;

    public Character(int hp, int lvl, int atk) {
        this.hp = hp;
        this.lvl = lvl;
        this.atk = atk;
    }
    
    public String get_name(){
        return name;
    }
    public int get_hp() {
        return hp;
    }
    public void set_hp(int hp){
        this.hp = hp;
    }
    public int get_lvl() {
        return lvl;
    }
    public void set_lvl(int lvl){
        this.lvl = lvl;
    }

    public int get_atk() {
        return atk;
    }
}

class Enemy extends Character {

    public final String name;

    public Enemy(int hp, int lvl, int atk, String name) {
        super(hp, lvl, atk);
        this.name = name;
    }

}

class Player extends Character {

    public Player(int hp, int lvl, int atk) {
        super(hp, lvl, atk);
    }
}
