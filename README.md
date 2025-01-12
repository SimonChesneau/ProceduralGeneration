![Alt text]([https://assets.digitalocean.com/articles/alligator/boo.svg "a title](https://github.com/SimonChesneau/ProceduralGeneration/blob/main/procedural%20generation.png)")
# ProceduralGeneration

The ProceduralGeneration code will create a random map based on multiple rules. Thoses maps are composed of multiple environlent based on color:

1. Beach: Yellow
2. Plain: Light green
3. Ocean: Blue
4. Forest: Dark green
5. Mountain: Grey
6. Snow: White 

Then the code will use Dijkstra and A* algorithms to try to find the fastest route from cell (1,1) to cell (150,150)

## How it works

The (*) Are done several times, here I set it to 10.

1. It creates all the environments and give them a specific weight (this value is used to tell how hard it will be to cross the cell) 
2. It will generate a square grid of the number of rows you chose. 
3. It will randomly color it with the colors of the beach, plain and ocean. 
4. (*)It will use a filter to go through every cell and look at their neighbours.
5. (*)Given the set of rulls set in the filter, it will change the current cell.
6. It will launch the Dijkstra and A* algorithm on the cells

A few parameters can be edited on the frame, but the environment weight have to be edited on the environment class.
