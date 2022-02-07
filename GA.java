import java.util.Random;

public class GA {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {

        Random rn = new Random();

        GA demo = new GA();
        
        //Initialize population
        demo.population.initializePopulation();

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);

        //While population gets an individual with maximum fitness
        while (demo.generationCount<1000) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();
            //Do mutation under a random probability
            
            demo.mutation();
            

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 13; i++) {
            System.out.print(demo.population.getFittest().genes[i]+" ");
        }

        System.out.println("");

    }

    //Selection
    void selection() {
    	for(int i = 0; i<population.individuals.length; i++) {
			int tempIndex = i;
			double tempMaxVal = population.individuals[i].fitness;
			Individual tempMax = population.individuals[i];
			for(int j = i+1; j<population.individuals.length; j++) {
				if(population.individuals[j].fitness>tempMaxVal) {
					tempMaxVal = population.individuals[j].fitness;
					tempMax = population.individuals[j];
					tempIndex = j;
				}
			}
			Individual temp = population.individuals[tempIndex];
			population.individuals[tempIndex] = population.individuals[i];
			population.individuals[i] = temp;
		}
    	for(int i = population.individuals.length/2; i<population.individuals.length;i++) {
    		population.individuals[i] = null;
    	}
    }

    //Crossover
    void crossover() {
    	double coPortion = Math.random();
    	for(int q = population.individuals.length/2; q<=population.individuals.length/2*(1+coPortion); q++) {
	    	Random rn = new Random();
	         //Select a random crossover point
	    	int randIndi1 = rn.nextInt(5);
	    	int randIndi2 = rn.nextInt(5);
	    	int crossOverPoint1 = rn.nextInt(6);
	        int crossOverPoint2 = crossOverPoint1+6;
	        int lb = Math.min(crossOverPoint1, crossOverPoint2);
	        int ub = Math.max(crossOverPoint1, crossOverPoint2);
	        int i = 0;
	        int k = lb;
	        int[] comp = new int[13-(ub-lb+1)];
	        int[] temp = new int[13];
	        for(int j = 0; j<13; j++) {
	        	int p = lb;
	        	while(population.individuals[randIndi2].genes[j]!=population.individuals[randIndi1].genes[p]&&p>=lb&&p<=ub) {
	        		p++;
	        	}
	        	if(p<ub+1) {
	        		continue;
	        	}
	        	else if(p==ub+1){
	        		comp[i] = population.individuals[randIndi2].genes[j];
	        		i++;
	        	}
	        }
	        i = 0;
	        for(int j = 0; j<13; j++) {
	        	if(j<lb||j>ub) {
	        		temp[j] = comp[i];
	        		i++;
	        	}
	        	else {
	        		temp[j] = population.individuals[randIndi1].genes[k];
	        		k++;
	        	}
	        }
	        Individual offSpring = new Individual(temp);
	        population.individuals[q] = offSpring;
    	}
    }

    //Mutation
    void mutation() {
    	int index = population.individuals.length-1;
    	while(population.individuals[index]==null) {
	        Random rn = new Random();
	        int mutationParent = (int)(Math.random()*25);
	        //Select a random mutation point
	        int mutationPoint1 = rn.nextInt(12);
	        int mutationPoint2 = rn.nextInt(12);
	        //Flip values at the mutation point
	        population.individuals[index] = population.individuals[mutationParent];
	        int temp = population.individuals[index].genes[mutationPoint1];
	        population.individuals[index].genes[mutationPoint1] = population.individuals[index].genes[mutationPoint2];
	        population.individuals[index].genes[mutationPoint2] = temp;
	        index--;
    	}
    }
}


//Individual class
class Individual {

    double fitness = 0.0;
    int[] genes = new int[13];
    int geneLength = 13;
    double distance[][] = {{0,30.2,34.7,35.4,32.4,29.2,28.4,28,15.6,22.2,12.2,28.3,39,36.6},
			{30.2,0,18.2,22.1,19.9,12,19.6,9.7,16.2,7.8,22,3.7,23.1,8.2},
			{34.7,18.2,0,8.3,5.8,23.4,13,22.2,17.7,14.9,19.9,14.7,7.6,9.9},
			{35.4,22.1,8.3,0,4,30.2,11.5,29,21.2,21.8,21.5,23.5,8.9,16.6},
			{32.4,19.9,5.8,4,0,28,7.1,26.8,13.3,15.1,15.3,21.3,10.1,14.3},
			{29.2,12,23.4,30.2,28,0,26.9,2.1,19.5,11.4,19.6,7.9,31.8,16.7},
			{28.4,19.6,13,11.5,7.1,26.9,0,28.7,11.6,16.3,14.2,20.9,13.3,17.2},
			{28,9.7,22.2,29,26.8,2.1,28.7,0,18.7,11.7,18.5,6.4,28.7,15.1},
			{15.6,16.2,17.7,21.2,13.3,19.5,11.6,18.7,0,7,3.1,14.8,20.7,21.3},
			{22.2,7.8,14.9,21.8,15.1,11.4,16.3,11.7,7,0,7.2,8.6,20.9,14.2},
			{12.2,22,19.9,21.5,15.3,19.6,14.2,18.5,3.1,7.2,0,14.4,21.9,22.6},
			{28.3,3.7,14.7,23.5,21.3,7.9,20.9,6.4,14.8,8.6,14.4,0,26.9,11.3},
			{39,23.1,7.6,8.9,10.1,31.8,13.3,28.7,20.7,20.9,21.9,24.9,0,15.4},
			{36.6,8.2,9.9,16.6,14.3,16.7,17.2,15.1,21.3,14.2,22.6,11.3,15.4,0}
			};
    public Individual() {
        int index = 0;
        //Set genes randomly for each individual
        while(true) {
        	int random = (int)(Math.random()*13+1);
        	int j = 0;
        	for(; j<geneLength; j++) {
        		if(random==genes[j]) {break;}
        	}
        	if(j==geneLength) genes[index++] = random;
        	if(index==geneLength) break;
        }
        fitness = 0;
    }
    public Individual(int[] gene) {
    	genes = gene;
    	fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        int d = 0;
        for (int i = 0; i < 12; i++) {
            d += distance[genes[i]][genes[i+1]];
        }
        d += distance[0][genes[0]];
        d += distance[genes[12]][0];
        fitness = 1.0/d;
    }

}

//Population class
class Population {

    int popSize = 128;
    Individual[] individuals = new Individual[popSize];
    double fittest = 0;
    //Initialize population
    public void initializePopulation() {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual();
        }
    }

    //Get the fittest individual
    public Individual getFittest() {
        double maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].fitness;
        return individuals[maxFitIndex];
    }

    //Get the second most fittest individual
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        double minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].fitness) {
                minFitVal = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calcFitness();
        }
        getFittest();
    }

}
