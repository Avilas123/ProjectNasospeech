#include<stdio.h>
#include "graphics.h"

const int WEIDTH=800, HEIGHT=600;

void drawLine(int moveToX, int moveToY,int drawX, int drawY, int color, int textX, int textY, char *name)
{
    moveto(moveToX, moveToY);
    setcolor(color);
    lineto(drawX, drawY);
    outtext(textX, textY, name);
}

int main()
{
    double x,y;
    int x, y=100;
    float q;
    moveto(WEIDTH/2, HEIGHT/2);
    if(x>=100)
        {
           printf("Invalid input");
        }
     else{
           q = (float)x/y;
           printf("The result is %f \n\n\n\n\n\n\n", q);
         }

     getch();
     closegraph();
    system("PAUSE");
    return 0;
}
