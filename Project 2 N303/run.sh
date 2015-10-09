#!/bin/sh
#$ -S /bin/sh
#$ -N nwen303_test
#$ -wd /vol/grid-solar/sgeusers/fred
#$ -pe nwen303_1.pe 4
#
echo ==UNAME==
uname -n
/usr/pkg/bin/mpirun -np $NSLOTS \
 /usr/pkg/java/sun-7/bin/java   \
   -classpath /am/rialto/home1/robertjona1/git/nwen303_project2/Project 2 N303/bin \
   Main