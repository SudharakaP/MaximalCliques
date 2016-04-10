## FILES

### facebook_ucsd.txt

This graph contains Facebook friendships between students at UCSD in 2005. 
This data was originally stored in a Matlab sparse matrix; however, this was 
processed using Python to create a more suitable format for reading with Java.
The edges in this file are directed; however, each edge also appears in reverse
order, making the final result undirected.

Source: https://archive.org/details/oxford-2005-facebook-matrix

Citation: Facebook data scrape related to paper "The Social Structure of Facebook 
Networks", by Amanda L. Traud, Peter J. Mucha, Mason A. Porter.

### facebook_1000.txt, facebook_2000.txt

These are smaller versions of facebook_ucsd.txt that only contain the first
1000 or 2000 vertices. Note that facebook_1000.txt is fully connected while
facebook_2000.txt is not. If you have an algorithm with a large runtime, it
may be helpful to use these files instead of a larger one.

### twitter_combined.txt

This graph consists of directed edges linking Twitter followers to the users
they follow. It is part of Stanford's Snap network database.

Source: https://snap.stanford.edu/data/egonets-Twitter.html

### twitter_higgs.txt

This graph consists of people who retweeted the messages of other people
at the time of the discovery of the Higgs boson. It is also part of the Snap
database.

Source: http://snap.stanford.edu/data/higgs-twitter.html

Note: In all of the above files, each line represents an edge from the vertex with the 
first number to the vertex to the second. 

### LinkedInData.txt

This is my LinkedIn connections graph. It contains 500 nodes and the first node represents me (which is the reason it has edges to all other nodes). 

Source: http://socilab.com/#home

### LinkedInReduced.txt

This is the same graph as above but the first node (the node that represents me) is removed from the graph. Hence the graph contains 499 nodes.

Source: http://socilab.com/#home

Note: In both LinkedInData.txt and LinkedInReduced.txt the graph is represented as an adjacency matrix where each is 1 or 0 depending on whether there is an edge between vertices represented by the corresponding row and column. 