
# Space Invaders Java Game

Welcome to the documentation for the Space Invaders Java game! This document provides an overview of the game, its rules, requirements, and instructions for running and playing it.

## Overview

Space Invaders is a classic arcade game where the player controls a spacecraft at the bottom of the screen, moving it horizontally to shoot at aliens. The objective is to survive as long as you can.

## Usage

Project contains **GameEngine** abstract class, any child of it is Space Invaders Game with your specific **view()** function to visualise game process.
Project also contains **SpaceInvadersConsole** as a console version of a game

## Rules
### Player Controls:

In console game implementation use **A** and **D** in console to move left and right relatively.

### Alien Movement:

In this game version aliens are static but do not relax ahead. Every enemy shoots each forth second, so you need to humiliate them faster then new aliens appear.

## Player Health:

The player has a limited number of lives - **5**.
Each bullet hit to our ship decrease lives by 1 till we lose all of them

## Requirements
To run the Space Invaders Java game, you need:

Java Development Kit (JDK):

Ensure you have JDK installed on your system. You can download it from Oracle's website.
IDE (Integrated Development Environment):

Any Java-compatible IDE such as IntelliJ IDEA, Eclipse, or NetBeans will work.
## Game Dependencies:
This game does not require any external dependencies.
Installation and Setup
Clone the repository from GitHub:

bash
git clone https://github.com/your-username/space-invaders-java.git
Open the project in your preferred IDE.

Build and run the project.

## Usage
Launch the game.

Aim to eliminate all the aliens.

Keep track of your lives.

Enjoy playing Space Invaders!
