package Simulation;

import java.util.*;

import Util.Calculator;

/**
 * This is a cancer cell. It is the most complex cell as it can attack tissue or immune cells, or grow into a dead cell.
 * For attacking tissue, it is a 1 hit replace it with a dead cell.
 * Immune cells are cooler. Each hit from a cancer cell lowers its strength by 1. When an immune cell reaches 0 strength
 * it dies!
 * <p>
 * It has a priority of action. If it can grow, it will grow. If it can kill a tissue cell, it will do that. Why?
 * Easiest way to grow is to kill a week tissue cell. If no other option, will attack immune cells. Path of
 * least resistance to growing basically.
 * <p>
 * Growing means turning a dead cell into a CancerCell.
 */

public class CancerCell extends Cell {

    // Constructor method
    public CancerCell(int x, int y) {
        super(1, x, y, 3);
    }

    // interactNeighbours method override
    @Override
    public void interactNeighbours(ArrayList<Cell> neighbours) {
        int cellToReplace = -1; // adjacentCells index of cancer cell to kill
        int cellToReplaceX; // X-coordinate of cancer cell to kill
        int cellToReplaceY; // Y-coordinate of cancer cell to kill
        int replaceIndex; // neighbours index of cancer cell to kill

        super.interactNeighbours(neighbours);

        // If dead cells present then cancer spreads
        if (this.getDeadCellCount() > 0) {
            for (int i = 0; i < adjacentCells.size() && cellToReplace == -1; i++) {
                // If dead cell, then break loop
                if (adjacentCells.get(i).getId() == 0) {
                    cellToReplace = i;
                }
            }

            if (cellToReplace >= 0) {
                // Retrieve coordinates of dead cell and calculate corresponding index in neighbours
                cellToReplaceX = adjacentCells.get(cellToReplace).getX();
                cellToReplaceY = adjacentCells.get(cellToReplace).getY();
                replaceIndex = Calculator.indexFromCoord(cellToReplaceX, cellToReplaceY);

                // Replace dead cell with cancer cell
                neighbours.set(replaceIndex, new CancerCell(cellToReplaceX, cellToReplaceY));
            }
        }
        // If at least one tissue cell is present and less immune cells are present
        if ((this.getTissueCellCount() > this.getImmuneCellCount()) && this.getTissueCellCount() > 0) {
            // Picks one tissue cell and kill it
            cellToReplace = -1;
            for (int i = 0; i < adjacentCells.size() && cellToReplace == -1; i++) {
                // If tissue cell, then break loop
                if (adjacentCells.get(i).getId() == 1) {
                    cellToReplace = i;
                }
            }

            if (cellToReplace >= 0) {
                // Retrieve coordinates of tissue cell and calculate corresponding index in neighbours
                cellToReplaceX = adjacentCells.get(cellToReplace).getX();
                cellToReplaceY = adjacentCells.get(cellToReplace).getY();
                replaceIndex = Calculator.indexFromCoord(cellToReplaceX, cellToReplaceY);

                // Replace tissue cell with dead cell
                neighbours.set(replaceIndex, new DeadCell(cellToReplaceX, cellToReplaceY));
            }
        }
        // If any immune cells present then combat ensues
        if (this.getImmuneCellCount() > 0) {
            // Picks one immune cell and challenges to combat
            cellToReplace = -1;
            for (int i = 0; i < adjacentCells.size() && cellToReplace == -1; i++) {
                // If immune cell then break loop
                if (adjacentCells.get(i).getId() == 4) {
                    cellToReplace = i;
                }
            }
            if (cellToReplace >= 0) {
                // Retrieve coordinates of immune cell and calculate corresponding index in neighbours
                cellToReplaceX = adjacentCells.get(cellToReplace).getX();
                cellToReplaceY = adjacentCells.get(cellToReplace).getY();
                replaceIndex = Calculator.indexFromCoord(cellToReplaceX, cellToReplaceY);

                // Lower strength of immune cell and replace with dead cell needed.
                neighbours.get(replaceIndex).setStrength(neighbours.get(replaceIndex).getStrength() - 1);
                if (neighbours.get(replaceIndex).getStrength() == 0) {
                    neighbours.set(replaceIndex, new DeadCell(cellToReplaceX, cellToReplaceY));
                }
            }
        }
    }
}