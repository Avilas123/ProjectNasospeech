#include<stdio.h>
#include<math.h>



int main()
{
 int x, y=100, a[10],i;
 float q,j,k;
 printf("Enters a number between 1-100 \n");
 scanf("%d", &x);
 if(x>=100)
 {
  printf("Invalid input");
 }
 else{
    q = (float)x/y;
    printf("The result is %0.2f \n\n\n\n\n\n\n", q);
     }


     for(i=0;i<9;i++)
     {

       printf("_______|");
     }
     printf("\n\n");
     for(j=0.0;j<=1.1;j=j+0.1)
         {

             printf("%0.2f    ", j);
             if(j>=q){

                printf("|");
                q++;

             }else{}
         }
         printf("\n\n\n\n");

 return 0;
}
