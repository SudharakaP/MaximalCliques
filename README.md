# MaximalCliques

This repository implements algorithms to find [Maximal Cliques]() and [Closeness Centrality]() of a social network graph. This project was done as part of the Capstone Project on Analyzing Social Network Data offered by the University of MaryLand in conjunction with Coursera. 

https://www.coursera.org/learn/intermediate-programming-capstone

## Overview

The project aims to find the [Closeness Centrality](https://en.wikipedia.org/wiki/Centrality#Closeness_centrality) of all the nodes in my LinkedIn network as well as the [Maximal Cliques](https://en.wikipedia.org/wiki/Clique_problem). The LinkedIn graph is visualized using a GUI with some interactivity. For a quick demo please download the JAR file under the releases page and double click on it to run the program. 

## Data 

The data set that I used for this project comes from my LinkedIn connections list. LinkedIn is a social networking site focused on professional connections and each member has the ability to connect with others in the network. Each connection is bidirectional; when one member sends a connection request, the other member has to approve it in order for the connection to form.

I obtained the connections/friendships between my list of connections through Socilab (http://socilab.com/#home) which uses the LinkedIn’s API to extract the graph of connections. However the number of nodes/connections that you can extract is limited to 499 (500 including yourself) due to LinkedIn’s API restrictions.

The data that I obtained comes as an adjacency matrix where the rows and columns represent each member of the graph and the elements are either 1 or 0 depending on whether two members (represented by the respective row and column) are connected or not. The size of this matrix is the number of connections extracted, which is 499 (500 including myself). The number of edges is the number of connections between these nodes which is found out to be, 3331.

## Algorithms 

For the Closeness Centrality was I used [Dangalchev's definition (http://www.sciencedirect.com/science/article/pii/S0378437105012768). 

## Setup

Importing Project into eclipse:

- Create a new Java Project in your workspace
- Import the starter files: File -> Import -> Select "File System" -> Next -> Browse and set root directory to folder contents of zip were extracted to -> Finish
