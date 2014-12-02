# War!

Implementation of the card game "War" in Java Swing for Prof. Jackie Horton's CS 110 class.

## Rules

From [Wikipedia](http://en.wikipedia.org/wiki/War_%28card_game%29):

>War is a card game typically involving two players. It uses a standard French playing card deck. Due to its simplicity, it is played most often by children.

>The deck is divided evenly among the players, giving each a down stack. In unison, each player reveals the top card of their deck – this is a "battle" – and the player with the higher card takes both of the cards played and moves them to the bottom of their stack.

>If the two cards played are of equal value, then there is a "war". Both players place the next card (or three) of their pile face down, depending on the variant, and then another card face-up. The owner of the higher face-up card wins the war and adds all six (or ten) cards on the table to the bottom of their deck. If the face-up cards are again equal then the battle repeats with another set of face-down/up cards. This repeats until one player's face-up card is higher than their opponent's.

>Most descriptions of War are unclear about what happens if a player runs out of cards during a war. In some variants, that player immediately wins. In others, the player may play the last card in their deck as their face-up card for the remainder of the war.

>Game designer Greg Costikyan has observed that since there are no choices in the game, and all outcomes are random, it cannot be considered a game by some definitions. However, the rules often do not specify in which order the cards should be returned to the deck. If they are returned in a non-random order, the decision of putting one card before another after a win can change the overall outcome of the game. The effects of such decisions are more visible with smaller size decks as it is easier for a player to card count, however the decisions can still affect gameplay if taken in standard decks.

## Variant

In this variant of War, both players place a single card face-down and a single card face-up during war. If a player runs out of cards during war, they lose immediately.

## Design

This program is designed from a "model-view" point of view. The core class, WarModel, tracks the game as it progresses and sends notifications to the implementation of WarView, which in this case, is WarGui. The WarGui class is made up of a collection of JPanel subclasses which make up the GUI for the player to interact with to play the game.

### WarSimulator

This program also includes a utility class, WarSimulator. The WarSimulator class simulates button presses as fast as possible to simulate a game of War as fast as possible. A button is included on the GUI to activate and deactivate the simulator.

## Documentation

The JavaDocs for this project can be found at https://windy1.github.com/War