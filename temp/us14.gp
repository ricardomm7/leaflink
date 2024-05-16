set terminal svg enhanced font 'Verdana,12'
set output 'goOut/executionTimeAnalysis.svg'

set title "Execution time as a function of graph size"

set xlabel "Graph size"
set ylabel "Execution time (ms)"
set grid

set style data points
set pointsize 2
set style circle radius 0.1

# Defina o delimitador correto
set datafile separator ";"

plot "goOut/US14_Data.csv" using 1:2 with points title ""
