package Simulation;

import java.util.*;

import Util.Calculator;
import Util.Pair;

/**
 * A tissue cell. It wants to grow, but not as much as cancer. Has a chance to turn a dead
 * cell into a live one every time step
 */

public class TissueCell extends Cell {

    // Constructor method
    public TissueCell(int x, int y) {
        super(0, x, y, 1);
    }

    // interactNeighbours method override
    @Override
    public void interactNeighbours(ArrayList<Cell> neighbours) {
        int cellToReplace = -1; // adjacentCells index of dead cell to replace
        int cellToReplaceX; // X-coordinate of dead cell to replace
        int cellToReplaceY; // Y-coordinate of dead cell to replace
        int replaceIndex; // neighbours index of dead cell to replace

        super.interactNeighbours(neighbours);

        if (this.getDeadCellCount() > 0 && Math.random() * 100 > 30) {
            // Picks one dead cell and grow into it
            for (int i = 0; i < adjacentCells.size() && cellToReplace == -1; i++) {
                // If dead cell, then break loop
                if (adjacentCells.get(i).getId() == 0) {
                    cellToReplace = i; // Assigns index of adjacentCells ArrayList to cellToReplace.
                }
            }

            if (cellToReplace >= 0) {
                // Retrieve coordinates of dead cell and calculate corresponding index in neighbours
                cellToReplaceX = adjacentCells.get(cellToReplace).getX();
                cellToReplaceY = adjacentCells.get(cellToReplace).getY();
                // Translates into index of neighbours
                replaceIndex = Calculator.indexFromCoord(cellToReplaceX, cellToReplaceY);

                // Replace cell with tissue cell
                neighbours.set(replaceIndex, new TissueCell(cellToReplaceX, cellToReplaceY));
            }
        }
    }

}
