# JPaint Project - Patrick Marre
Date: 8.20.2021

## Notes on Features & Bugs 
### Missing Features:
- Group / Ungroup redo & undo

### Bugs:
- Grouped shapes that are moved cause the outline to drop below the shapes 
- When undoing a grouped shape move, all previous positions are printed out 
- Selecting the same item multiple times causes a new offset border to appear
- Creating second group merges groups together 
- Triangle outline offset is off on the hypotenuse side
- Bugs with undo / redo, specifically revolving around group, ungroup, and move

### Miscellaneous Notes: 
I ended up relying heavily on loops which is detrimental to performance. Part of this is due to not thinking more long term with my design and building while focused on the single task. This led to issues later on and, due to time constraints, pushed me to use more loops. I could have been more thoughtful in the planning process prior to starting to build out the program.

## Github Link
**Link:** www.github.com/pmarre/jpaint

## Design Patterns
### Command Pattern
*Used By: ICommand, CreateShapeCommand, DrawShapeCommand, UndoCommand, RedoCommand, MoveCommand, CopyCommand, DeleteCommand, GroupCommand, UngroupCommand, SelectCommand, PasteCommand*

The command pattern is my most widely used pattern that is implemented by 11 different classes. Essentially every click from the user is captured then delegated to the corresponding command which then executes. This pattern helps keep each class as simple as possible and keep each class focused on only one task. 






### Observer Pattern
*Used By: ShapeCollection, IObserver*

The observer pattern is used by my ShapeCollection class. In this pattern I register observers by adding them to a list and with every change (add, replace, delete, etc.) observers are notified and the canvas is redrawn. This solves the issues of having to individually call for the shape to be repainted in separate classes. 

### Singleton Pattern
*Used By: GraphicsSingleton, PaintCanvas, DrawShapeCommand*

The singleton pattern keeps only one Graphics2D alive at a time. Every time it is called, it creates a new instance and passes the new Graphics2D to the necessary classes ensuring that all shapes are being properly repainted. 



### Strategy Pattern
*Used By: DrawShapeCommand, DrawEllipseStrategy, DrawRectangleStrategy*

The strategy pattern helps delegate work out to the correct classes. When DrawStrategy is called with a shape, the ShapeType is discovered and sent to the correct strategy class to handle the shape. This patterns keeps the actual shape strategy class to act independently of the draw shape command allowing multiple shapes to use the same draw shape strategy command.

### Null Object Pattern 
*Used By: NullObject, ICommand, GroupCommand*

The null object pattern is used to prevent repetitive null checks and prevent any errors related to an object being null. When the constructor of the class has an object that is null the NullObject class is created which contains no code. This prevents the need to constantly check for null and if a method is called on a null object there is no error. 




## Success and Failures

This was really my first experience working on a project of this scale and I quickly learned that they only way to effectively work through the project was to breakdown big problems into smaller ones. By taking smaller steps, solutions arose much quicker than if I were to work on the big problem. Another success was learning to use design patterns to improve development. By focusing on the design and strategy of the code before actually starting to write any code, I was able to improve my workflow and know in advance what code needed to be written. Both of these successes really work together and will help lead to better code. 

One of my biggest issues was getting too much tunnel vision when working on a problem and not thinking more scalable. I found myself repeatedly coding myself into a corner and having to find a less than ideal solution or make a major refactor. I could have saved countless hours refactoring by spending more time early on thinking about the relationship of items and the information that needs to be passed.  Another one of my issues, which relates to the previous, was coming up with inefficient solutions. I found myself being dependent on for loops and even utilizing nested for loops which would lead to poor performance in the long term. With more time, I would look to refactor the code to make it a bit more optimized. 

