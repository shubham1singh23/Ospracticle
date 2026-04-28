% -------- FACTS --------
parent(john, mary).
parent(john, sam).
parent(susan, mary).
parent(susan, sam).

parent(mary, alice).
parent(mary, bob).

male(john).
male(sam).
male(bob).

female(susan).
female(mary).
female(alice).

% -------- RULES --------
father(X, Y) :- parent(X, Y), male(X).

mother(X, Y) :- parent(X, Y), female(X).

sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

grandparent(X, Y) :- parent(X, Z), parent(Z, Y).

grandfather(X, Y) :- grandparent(X, Y), male(X).

grandmother(X, Y) :- grandparent(X, Y), female(X).
