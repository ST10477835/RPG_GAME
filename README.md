# 🏰 Dungeon Challengers – Text-Based RPG

A simple **Java console RPG** where you explore a dungeon and battle randomly generated enemies. Level up, manage your health, and survive as long as you can—after **5 defeats it’s Game Over**.

---

## ✨ Features

- **Turn-based combat**: Attack, Heal, or Run.  
- **Critical hits**: 10% chance (or guaranteed after an enemy fails a heal).  
- **Enemy AI**: Attacks, heals, or (rarely) fails to heal.  
- **Text health bars** for you and the enemy (0–30 blocks).  
- **Level ups**: Gain a level after each victory.  
- **Replay loop**: Play again without restarting the app.  

---

## 🕹️ How to Play

1. Launch the game and choose whether to start (`y`) or exit (`n`/`exit`).  
2. Enter your username (first time only).  
3. When you encounter an enemy, choose:
   - `1` → **Attack** – 75% hit chance; 10% crit (double damage).  
   - `2` → **Heal** – 75% success; heals up to max 30 HP.  
   - `3` → **Run Away** – 50% chance to escape.  
4. Defeat enemies to **level up**.  
5. If you die, you’re revived with **10 HP**. After **5 deaths**: **GAME OVER**.  

> **Note:** Inputs are lowercase: `y`, `n`, `exit`, and numeric choices `1/2/3`.  

---

## 📊 Mechanics (Probabilities & Rules)

| Action / Event                     | Chance / Effect                                                                 |
|-----------------------------------|----------------------------------------------------------------------------------|
| Player attack hits                | 75% (miss 25%)                                                                   |
| Player critical hit               | 10% (or **guaranteed** immediately after enemy fails a heal)                    |
| Player heal success               | 75% (fails 25%); +5 HP if ≤25 HP, otherwise heal to 30                          |
| Run away                          | 50% success                                                                      |
| Enemy low-HP behavior (< 5 HP)    | 87.5% heal **+10 HP**; 12.5% **fail** (your next attack is guaranteed crit)     |
| Enemy normal behavior (≥ 5 HP)    | 80% attack; 20% heal **+5 HP**                                                   |
| Enemy level vs player             | 87.5% equal to player level; 12.5% **one level higher**                          |

Enemy names are chosen at random (e.g., **Varkon the Cruel**, **Xalther the Wicked**, etc.).  

---

## 🖥️ Example Combat

## 🖥️ Sample Gameplay
≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈  
You have encountered a wild, Varkon the Cruel  
Your Stats Enemy Stats  
Name: Henry | Name: Varkon the Cruel  
Level: 10 | Level: 10  
Attack: 10 | Attack: 9  
Health: 10 | Health: 14

Would you like to fight it? (y/n)

y
≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈  
Henry HP:[██████------------------------]  
Varkon HP:[██████████--------------------]  
Select an option  

Attack

Heal

Run Away  
≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

1
You have successfully attacked Varkon!
-10hp

---

## 🚀 Getting Started

### Prerequisites
- Java **17+** (works with most modern JDKs/IDEs)  
- Optional: NetBeans / IntelliJ IDEA / Eclipse  

### Compile & Run (Command Line)

From the project’s **`src`** root (so the `rpg_game` folder is visible):

```bash
# Compile
javac rpg_game/RPG_GAME.java

# Run (note the package-qualified name)
java rpg_game.RPG_GAME

Troubleshooting:
If you see Error: Could not find or load main class rpg_game.RPG_GAME, make sure you:

Run from the directory above rpg_game/

Use the exact package name rpg_game

Compiled successfully before running

Run in NetBeans (or any IDE)

Create/open a Java project and ensure the package is rpg_game.

Place RPG_GAME.java under src/rpg_game/.

Set RPG_GAME as the Main Class.

Click Run.

src/
└── rpg_game/
    └── RPG_GAME.java   # Contains: RPG_GAME (main), Character (abstract), Enemy, Player
```
## 🛠️ Troubleshooting

If you see the error:

Error: Could not find or load main class rpg_game.RPG_GAME

make sure you:

1. Run from the directory **above** `rpg_game/`.
2. Use the exact package name `rpg_game`.
3. Compile successfully before running.

### Run in NetBeans (or any IDE)

1. Create/open a Java project and ensure the **package** is `rpg_game`.  
2. Place `RPG_GAME.java` under `src/rpg_game/`.  
3. Set `RPG_GAME` as the **Main Class**.  
4. Click **Run**.

This keeps:

-The main heading for troubleshooting.
-The error in a fenced code block.
-Steps as numbered lists for clarity.

IDE instructions under a subheading.
