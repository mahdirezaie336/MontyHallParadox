#include<stdio.h>
#include<stdlib.h>
#include<time.h>

// This is a simple program that tests the Monty Hall
// Paradox in statistics. It creates an array with size
// 3 filled with 0s and randomly puts a 1 into one of them.
// Then randomly chooses on of them. Then one of the two
// other boxes which is not empty will be opened and player
// changes the choice.
//
// We will see in about 6000 times of 10000 times the
// the choice is true.
//
// Author: Mahdi Rezaie
// Email: mahdi.rezaie.336@gmail.com
//
int main()
{
    srand(time(0) * time(0));
    
    int i, correct = 0;
    
    // Doing experiment 10000 times
    for(i = 0; i < 10000; ++i)
    {
        // Choices array
        int arr[3] = {0,0,0};
        arr[rand()%3] = 1;
        
        // Let the player choose
        int choose = rand()%3;
        
        // The man in TV program opens one of
        // two other boxes which is not empty.
        int toOpen;
        do
        {
            toOpen = rand()%3;
        }
        while((toOpen == choose) || (arr[toOpen] == 1));
        
        // In this part, player chooses the other box.
        // If it is filled with 1, adds 1 to corrects.
        int j;
        for(j = 0; j < 3; ++j)
        {
            if(j == toOpen || j == choose)
                continue;
                
            if(arr[j] == 1)
                correct++;
            
            break;
        }
    }
    
    printf("Total tests: %d\n", i);
    printf("Corrects: %d\n",correct);
    return 0;
}