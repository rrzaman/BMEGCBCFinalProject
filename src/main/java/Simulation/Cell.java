package Simulation;

import java.util.*;

import Util.Calculator;

/**
 * The default, boring cell.
 */

public class Cell {
    private int strength, x, y, id;
    protected int deadCellCount = 0, cancerCellCount = 0, immuneCellCount = 0, tissueCellCount = 0;
    private static HashMap<String, Integer> concentrations;
    private static HashSet<String> signals;
    protected static ArrayList<Cell> adjacentCells;


    // Default constructor method for Cell
    public Cell() {
        setStrength(0);
        setX(0);
        setY(0);
        setId(0);
        concentrations = new HashMap<>();
        signals = new HashSet<>();
        adjacentCells = new ArrayList<>();
    }

    // User-inputted constructor method for Cell
    public Cell(int strength, int x, int y, int id) {
        setStrength(strength);
        setX(x);
        setY(y);
        setId(id);
        concentrations = new HashMap<>();
        signals = new HashSet<>();
        adjacentCells = new ArrayList<>();
    }

    // Concentration HashMap constructor and setter method
    public static void setConcentration(String chemical, int conc) {
        concentrations.put(chemical, conc);
    }

    // Signal molecule HashSet constructor and setter method
    public static void setSignal(String molecule) {
        signals.add(molecule);
    }

    // Adjacent cell ArrayList constructor and setter method
    public static void setAdjacentCells(Cell neighbour) {
        adjacentCells.add(neighbour);
    }

    // interactNeighbours method
    public void interactNeighbours(ArrayList<Cell> neighbours) {
        int currentCellIndex;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                // Skips the cell in question as coordinates will be (x,y)
                if (i == 1 && j == 1) {
                    continue;
                }
                // Gets index of cell in question to store in adjacentCells ArrayList.
                currentCellIndex = Calculator.indexFromCoord(x - 1 + i, y - 1 + j);
                // Stores adjacent cells for future referencing. Cell index is restricted to above zero to
                // account for adjacent corner cells which do not have array values (they do not exist).
                if (currentCellIndex >= 0) {
                    setAdjacentCells(neighbours.get(currentCellIndex));

                    // Counter for future reference
                    switch (neighbours.get(currentCellIndex).getId()) {
                        case 0 -> deadCellCount++;
                        case 1 -> tissueCellCount++;
                        case 3 -> cancerCellCount++;
                        case 4 -> immuneCellCount++;
                    }
                }
            }
        }
    }

    // Strength setter method
    public void setStrength(int strength) {
        this.strength = Math.max(strength, 0);
    }

    // Strength getter method
    public int getStrength() {
        return this.strength;
    }

    // X-coordinate setter method
    public void setX(int x) {
        this.x = Math.max(x, 0);
    }

    // X-coordinate getter method
    public int getX() {
        return this.x;
    }

    // Y-coordinate setter method
    public void setY(int y) {
        this.y = Math.max(y, 0);
    }

    // Y-coordinate getter method
    public int getY() {
        return this.y;
    }

    // ID setter method
    public void setId(int id) {
        this.id = Math.max(id, 0);
    }

    // ID getter method
    public int getId() {
        return this.id;
    }

    // Dead cell getter method.
    public int getDeadCellCount() {
        return this.deadCellCount;
    }

    // Cancer cell getter method.
    public int getCancerCellCount() {
        return this.cancerCellCount;
    }

    // Immune cell getter method.
    public int getImmuneCellCount() {
        return this.immuneCellCount;
    }

    // Tissue cell getter method.
    public int getTissueCellCount() {
        return this.tissueCellCount;
    }
}


