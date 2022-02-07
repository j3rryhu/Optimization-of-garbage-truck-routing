# Optimization of garbage truck routing
1. **Introduction**

   Garbage sorting has been a hot topic since March 2021 in Chengdu. The laws have been released to regulate garbage sorting. Logistics is an important part of garbage sorting, and transportation planning can help save a great deal of money. I devised a model to help with the optimization of transportation route. In order to save money, the goal is to minimize the total distance travelled by the garbage truck. I use GoogleMap to locate the garbage stations and choose 1 garbage disposal station and 13 garbage transfer stations as study subjects. The route is shown in the graph in the results section. The distances between stations are stored in a 14 by 14 symmetric matrix, D. In this way, the problem becomes the classic travelling salesman problem. I used genetic algorithm as the solution to the problem. Genetic algorithm first generates a population of random solutions which are called individuals. The individuals are defined as an array of length thirteen, which represents the order in which the truck will pass each station, ignoring the start and end point (disposal station 0). Then, the algorithm simulates the process of selection, crossover and mutation, respectively referring to different operations on these individuals. Finally, after many iterations, the results converge on a single solution, the final result.

2. **The number for chosen garbage transfer stations:**

   0. Shuangliu kitchen waste disposal station

   1. Chengdu University of Technology garbage transfer station

   2. Jinniu district quan shui sheng huo garbage transfer station

   3. Jinniu district luo jia garbage transfer station

   4. Xing sheng sheng huo garbage transfer station

   5. Urban Vocational College of Sichuan garbage transfer station

   6. Jinniu district jin quan sheng huo garbage transfer station

   7. Damian garbage transfer station

   8. Shuangnan Biyun garbage transfer station

   9. Qunzhong road garbage transfer station

   10. Hongpai lou garbage transfer station

   11. Dongsheng shenghuo garbage transfer station

   12. Huamei community garbage transfer station

   13. Xinshan shenghuo garbage transfer station
   <p align="center">
   <img src="Garbage station sites.jpg" />
   </p>
       

3. Results:

   The final route given by the program is 0->8->6->4->3->12->2->13->1->11->7->5->9->10->0. The total distance is approximately 110 kilometers.
   <p align="center">
   <img src="Transportation Route.jpg" />
   </p>

4. Acknowledgement

   Location of garbage stations: 
   https://www.google.com/maps/
