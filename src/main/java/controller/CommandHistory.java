package controller;

import java.util.Stack;

import model.interfaces.IShape;
import model.interfaces.IUndoable;



class CommandHistory {

	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		System.out.println("added: " + cmd);
		redoStack.clear();
	}

	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			System.out.println(c);
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			System.out.println(c);
			undoStack.push(c);
			c.redo();
		}
		return result;
	}
}
