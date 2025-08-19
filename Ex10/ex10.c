#include <stdio.h>
#include <string.h>

void main() {
    char data[100], div[20], temp[100];
    int i,j,datalen,divlen;
    
    printf("Enter data: ");
    scanf("%s", data);
    printf("Enter divisor: ");
    scanf("%s", div);
    
    datalen = strlen(data);
    divlen = strlen(div);
    
    for(i=0;i<divlen-1;i++)
        data[datalen+i] = '0';
    data[datalen+i] = '\0';
    
    strcpy(temp,data);
    
    for(i=0;i<datalen;i++) {
        if(temp[i]=='1') {
            for(j=0;j<divlen;j++) {
                temp[i+j] = (temp[i+j]==div[j]) ? '0' : '1';
            }
        }
    }
    
    printf("CRC code: %s%s\n", data, temp+datalen);
}
