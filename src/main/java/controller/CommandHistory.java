package controller;

import java.util.Stack;

import model.interfaces.IShape;
import model.interfaces.IUndoable;



class CommandHistory {

	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		System.out.println("Added to CH: " + cmd);
		redoStack.clear();
	}

	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			System.out.println("Undo from CH: " + c);
			redoStack.push(c);
			System.out.println(("added to redoStack: " + redoStack.get(0)));
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		System.out.println("redo CH " + redoStack.size());
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			System.out.println("Redo from CH: " + c);
			undoStack.push(c);
			c.redo();
		}
		return result;
	}
}
