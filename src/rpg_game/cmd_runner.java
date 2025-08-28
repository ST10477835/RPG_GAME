/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg_game;

import java.io.IOException;

public class cmd_runner {
    public static void main(String[] args) {
        // Only relaunch if not already running in CMD
        if (args.length == 0 || !args[0].equals("--cmd")) {
            try {
                // Path to the folder containing your package (top-level)
                String classPath = "C:\\Users\\abule\\OneDrive\\Documents\\NetBeansProjects\\RPG_GAME\\build\\classes";

                // Command to open a new CMD and run the class
                String cmd = String.format(
                        "cmd /c start cmd.exe /K \"cd /d \"%s\" && java rpg_game.RPG_GAME --cmd\"",
                        classPath
                );

                // Execute the command
                Runtime.getRuntime().exec(cmd);

                // Exit this NetBeans process
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // === GAME LOGIC STARTS HERE ===
        System.out.println("Welcome to the Dungeon!");
        // Add your animations, menus, etc.
    }
}

