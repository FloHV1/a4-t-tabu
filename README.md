# How does your program use inheritance, abstraction, and polymorphism? (refer to specific line numbers in your code)

- Inheritance
    - The Figure, BoardGame, and Puzzle classes inherits the AvailiableCount attributes from the PhysicalProduct Superclass which inherits the "_SKU, "_name", and "_price" from the Products SuperClass.
        src/Products/PhysicalProducts.java/ (Line 3, 23, 52, 81)

    - The Videogame class inherits the "_team" attribute from the DigitalProduct Superclass, which inherits the "_SKU, "_name", and "_price" attributes from the Products SuperClass.
        src/Products/DigitalProducts.java/ (Line 11)

    - Each subclass also has access to the public methods from the parent class.

- Abstraction
   - I made the PhysicalProduct class an abstract class because every physical product must be either a Figure, a Puzzle, or a BoardGame. (src/Products/PhysicalProducts (Line 3))

- Polymorphism
    - I used method overriding in each of the PhysicalProduct subclasses tp Override the ToString method of the Superclass in order to include their own unique attribute.
    src/Products/PhysicalProducts.java/ (Line 44, 73, 102)

# Suppose the store wants to add a new product that - like video games - has no physical inventory. Say it’s a 1-hour virtual reality arcade session. How does your design accommodate these kinds of additions without requiring major rewrites?

    I made a DigitalProducts class with the attribute "_team" instead of making the "_team" attribute unique to video games. This way I would be able to create subclasses of the DigitalProducts superclass such as a VR session and have them inherit the "_team" attribute.

# What did you learn from how you designed and coded the assignment, and given the chance to do it again, what (if anything) would you change, and why?

    Making UML diagram was a big help, especially having it graded and getting feedback before I got to coding, so I'll probably make UML diagrams more often. If i could do one thing differently, I'd probably make unit tests as soon as im done coding a method, rather than waiting until the program is functioning as it kind of defeats the purpose
