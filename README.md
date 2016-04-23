# MaximalCliques

This repository implements algorithms to find [Maximal Cliques]() and [Closeness Centrality]() of a social network graph. This project was done as part of the Capstone Project on Analyzing Social Network Data offered by the University of MaryLand in conjunction with Coursera. 

https://www.coursera.org/learn/intermediate-programming-capstone

## Overview

The project aims to find the [Closeness Centrality](https://en.wikipedia.org/wiki/Centrality#Closeness_centrality) of all the nodes in my LinkedIn network as well as the [Maximal Cliques](https://en.wikipedia.org/wiki/Clique_problem). In addition the LinkedIn graph is visualized using a GUI with some interactivity. For a quick demo please download the JAR file under the releases page and double click on it to run the program. 

## Data 

The data set that I used for this project comes from my LinkedIn connections list. LinkedIn is a social networking site focused on professional connections and each member has the ability to connect with others in the network. Each connection is bidirectional; when one member sends a connection request, the other member has to approve it in order for the connection to form.

I obtained the connections/friendships between my list of connections through Socilab (http://socilab.com/#home) which uses the LinkedIn’s API to extract the graph of connections. However the number of nodes/connections that you can extract is limited to 499 (500 including yourself) due to LinkedIn’s API restrictions.

The data that I obtained comes as an adjacency matrix where the rows and columns represent each member of the graph and the elements are either 1 or 0 depending on whether two members (represented by the respective row and column) are connected or not. The size of this matrix is the number of connections extracted, which is 499 (500 including myself). The number of edges is the number of connections between these nodes which is found out to be, 3331.

## Algorithms and Data Structures:

### Goal 1: Calculate the Closeness Centrality of all the nodes in the graph.

The Centrality of a graph is a measurement to identify the most important vertices. There are several centrality measurements and I chose to explore closeness centrality as the first part of this project. The closeness centrality of an undirected graph is defined by Dangalchev in the following paper.

http://www.sciencedirect.com/science/article/pii/S0378437105012768

Although there are several definitions of closeness centrality the one given by Dangalchev is the most appropriate in the case of non-connected graphs (which is most often the case with social network graphs). To find the closeness centrality of a node according to the definition of Dangalchev, we need to find the shortest distance between that node and every other node in the graph. These shortest distances were obtained by using the Breadth First Search algorithm. As for the data structures, the graph was stored as an adjacency list and I think given the sparseness of the graph this is appropriate. 

### Goal 2: Find all the Maximal Cliques of the graph. (Clique Problem).

A Clique of a graph is a set of vertices that forms a Complete Sub-graph. A maximal clique is a clique that cannot be extended by including one more adjacent vertex. In this part of the project I aim to find all the maximal cliques of the graph. This is commonly known as the Clique Problem and is NP-complete. Although there are algorithms to find all the maximal cliques of a graph they all have exponential running time. In solving this problem I plan to use the Bron–Kerbosch algorithm which is the most common one that is being used to tackle this problem currently. The maximal cliques of a graph give an insight into which subsets of nodes are closely knit. In other words in a social network the maximal cliques represent clusters where all members know each other.

The below link points to the set of maximal cliques of the LinkedIn graph that I analyzed.

https://docs.google.com/document/d/1S5Q3xp57JQC7TVGtgdpHhDs-OYAvOEOGtFUlyypdf0s/edit?usp=sharing

I also made a graph visualization with some interactive elements using the GraphStream library (http://graphstream-project.org/). The graph is drawn using the GraphStream library and I implemented the following functionalities.

- Show the Maximum Clique (Clique of largest size) by highlighting the corresponding nodes in red.

- When clicked on any node the Closeness Centrality of the node will be displayed.

- Pressing “C” on the keyboard will show all the Closeness Centralities.

- Pressing “I” on the keyboard will return to the default labeling which is the indices of the nodes.

- Pressing “H” on the keyboard gives a small information dialog to help the user with the key controls (this dialog automatically appears when the application starts).

- The following functionalities are already supported by GraphStream library.

- Arrow keys can be used to move around the graph.

- Page-Up and Page-Down keys zoom in and out.

- Shift+R returns to the default zoom level.

If you are interested in running this application, I have packaged it in a runnable JAR file and you can download it in the releases section above. 

Just double click on it to start the application. Note that it make take a few seconds to load the application and I have added a Splash Screen to reduce your boredom while waiting.

## Correctness verification (i.e. testing):

Correctness verification of all the methods were done by performing unit tests with JUnit. The tests that were performed are listed below.

- Test all methods on an empty graph (graph with no edges or vertices).

- Test all methods implemented on a small graph with 5 vertices and 12 edges.

- Test for edge cases such as adding already added vertices, adding already added edges.

- Test closeness centrality for nodes that were not connected to graph (which as expected, resulted in a return value of -1).

- Test the Bron-Kerbosch algorithm for empty graph and checked whether it gives a null set.

- Test the Bron-Kerbosch algorithm for small graphs (e.g. a graph with 5 vertices and 12 edges).

Note that some of the methods, such as the ones in the “application” package were tested by running the program with different inputs. This includes all functionality associated with the GUI. I tested the GUI with different input and edge cases to see if the behaviour is as expected.

**Note:** Along with the code I have also included a file named, ClassDiagram.ucls which is generated using the OpenAid UML Explorer for Eclipse. 

## Setup

Importing Project into eclipse:

- Create a new Java Project in your workspace
- Import the starter files: File -> Import -> Select "File System" -> Next -> Browse and set root directory to folder contents of zip were extracted to -> Finish
