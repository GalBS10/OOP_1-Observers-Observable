# OOP_1-Observers-Observable
#observer design pattern
The observer design pattern is a behavioral design pattern that defines a one-to-many dependency between objects.
It allows one object (the subject) to notify other objects (the observers) about changes to its state.
The observer pattern is used to create a loose coupling between objects. Loose coupling means that the objects are not tightly interconnected,
so that changes to one object do not affect the other objects.
This can be useful in a number of situations, such as when you want to notify multiple objects about changes to a shared resource,
or when you want to update multiple views of the same data. This makes the system more flexible and easier to maintain.

#overview
In our code the observable is the object of GroupAdmin type which implements Sender and the observers are ConcreteMember type which implements Member .
we are making an object of type - GroupAdmin, that holds a string called "State" which the observers depened on. we can use methods from UndoableStringBuilder class in order to change the string and then we notify all the registered observers.
In addition, we can make several objects of type - ConcreteMember which will be the observers .
Those objects can be registered to the GroupAdmin. 


#how does the code works

we have 2 classes. GroupAdmin class which implements Sender and ConcreteMember which implements Member.

#GroupAdmin
This class implements the class Sender. A GroupAdmin has an arraylist of members who are registered to him.
In addition, it has a state field which holds the string that we can change by using the methods that implemented in GroupAdmin class.
As we alredy said the "State" is what the ConcreteMember (the observers) depened on.

#ConcreteMember
This class implements the class Member. A ConcreteMember implements only one method - update() . The register() function in GroupAdmin and notify() function use the update method in order to update all the members that have already been registered to it and give them the latest version of the GroupAdmin's State(String) . 


