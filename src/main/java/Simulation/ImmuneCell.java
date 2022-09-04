package Simulation;

import java.util.*;
import Util.Calculator;
import Util.Pair;

/**
 * The immune cell! It kills cancer, and has a chance to attack multiple cancer cells per turn!
 */

public class ImmuneCell extends Cell {

    // Constructor
    public ImmuneCell(int x, int y) {
        super(3, x, y, 4);
    }

    // interactNeighbours method override
    @Override
    public void interactNeighbours(ArrayList<Cell> neighbours) {
        int cellToReplace = -1; // adjacentCells index of cancer cell to kill
        int cellToReplaceX; // X-coordinate of cancer cell to kill
        int cellToReplaceY; // Y-coordinate of cancer cell to kill
        int replaceIndex; // neighbours index of cancer cell to kill

        super.interactNeighbours(neighbours);

        if (this.getCancerCellCount() > 0) {
            // Picks one cancer cell and kill it
            for (int i = 0; i < adjacentCells.size() && cellToReplace == -1; i++) {
                // If cancer cell, then break loop
                if (adjacentCells.get(i).getId() == 3)  {
                    cellToReplace = i;
                }
            }

            if (cellToReplace >= 0) {
                // Retrieve coordinates of dead cell and calculate corresponding index in neighbours
                cellToReplaceX = adjacentCells.get(cellToReplace).getX();
                cellToReplaceY = adjacentCells.get(cellToReplace).getY();
                replaceIndex = Calculator.indexFromCoord(cellToReplaceX, cellToReplaceY);

                // Replace cell with tissue cell
                neighbours.set(replaceIndex, new DeadCell(cellToReplaceX, cellToReplaceY));
            }
        }

    }

}