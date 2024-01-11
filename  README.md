# pz1-readers-writers

Project created for [Programowanie Zaawansowane 1](https://sylabusy.agh.edu.pl/en/1/2/18/1/4/16/140) course at AGH University of Krakow.

## Project description

Create a simple program implementing the classic problem of concurrency: *Readers-Writers Problem*.

Create a `Library` class, which has methods necessary to enter and leave it. Each one of them should print information
to standard output about the amount of readers/writers in queue and inside the library, and who enters/leaves at the
given time.

Assume that:
- Library can be occupied by one writer only, who has to be alone while inside
- Library can be occupied by up to 5 readers
- No thread gets starved